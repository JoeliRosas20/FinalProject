package com.example.finalprojectapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NeedActivity extends AppCompatActivity {
    private FloatingActionButton addFloaty;
    private Button removeButt;
    private TextView listNames;
    private static final String NAME = "GotActivity";
    ListItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_activity);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        addFloaty = findViewById(R.id.Need_floaty);
        removeButt = findViewById(R.id.Need_remove);
        listNames = findViewById(R.id.list);
        addFloaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tent = new Intent(v.getContext(), NeedAddActivity.class);
                startActivityForResult(tent, 1);
            }
        });
        removeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //notification button
        Button createNotificationsButton = findViewById(R.id.button_notifications);

        createNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(NAME, "Inside Notification onClick");
                // intializes the function below
                addNotifications();
            }
        });
        Log.d(NAME, "Leaving onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(NAME, "Inside the onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(NeedAddActivity.ADDED);
                Log.d("Got", "Reply:"+reply);
                listNames.setText(reply);
                listNames.setVisibility(View.VISIBLE);
            }
        }
        Log.d(NAME, "Leaving the onActivityResult");
    }

    public void SearchStore(View view) {
        Log.d(NAME, "Inside SearchStore method");
        Uri addressUri = Uri.parse("geo:0,0?q=+shoes+store");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
        Log.d(NAME, "Leaving SearchStore method");
    }

    public void SearchSite(View view) {
        Log.d(NAME, "Inside SearchSite method");
        String url = "https://shopping.google.com/?nord=1";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        Log.d(NAME, "Inside SearchSite method");
    }

    private void addNotifications() {
        Log.d(NAME, "Inside addNotifications");
        //Add as a Notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("primary_notification_channel", "Need Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");
            manager.createNotificationChannel(notificationChannel);
        }
        // Building the notifications
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Final Project Notifications")
                .setContentText("An item has just been added, check it out ")
                .setContentText("Hypebeast shoes has been added to your shopping list");

        // Creates the intent needed to show the notifications
        Intent notificationIntent = new Intent(this, NeedActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);


        manager.notify(0,builder.build());
        Log.d(NAME, "Leaving addNotifications");



    }
}
