package com.example.finalprojectapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NeedActivity extends AppCompatActivity {
    private FloatingActionButton addFloaty;
    private Button removeButt, Sites_btn, Store_btn;
    private TextView listNames;
    private static final String NAME = "NeedActivity";
    private NotificationManager manager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    Button createNotificationsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_activity);
        addFloaty = findViewById(R.id.Need_floaty);
        removeButt = findViewById(R.id.Need_remove);
        Sites_btn = findViewById(R.id.Sites_btn);
        Store_btn = findViewById(R.id.Store_btn);
        listNames = findViewById(R.id.list);
        //Include the following 2 in NeedActivity and GotActivity
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Store SharedPreference value here so that it goes into the helper
        loadBCFromPref(sharedPreferences);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
        createNotificationsButton = findViewById(R.id.button_notifications);
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        createNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(NAME, "Inside Notification onClick");
                String toastMessage;
                long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                long triggerTime = SystemClock.elapsedRealtime();
                if (alarmManager != null) {
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
                }
                toastMessage = "Reminder On!";
                Toast.makeText(NeedActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
        addNotifications();
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
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Need Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies user of items in Need List");
            manager.createNotificationChannel(notificationChannel);
        }
        Log.d(NAME, "Leaving addNotifications");



    }
    private void loadBCFromPref(SharedPreferences sharedPreferences) {
        changeBC(sharedPreferences.getString(getString(R.string.color_choices), "#0000FF"));
    }

    private void changeBC(String color) {
        switch (color) {
            case "#0000FF":
                removeButt.setBackgroundColor(Color.BLUE);
                Sites_btn.setBackgroundColor(Color.BLUE);
                Store_btn.setBackgroundColor(Color.BLUE);
                break;
            case "#FFFF0059":
                removeButt.setBackgroundColor(Color.RED);
                Sites_btn.setBackgroundColor(Color.BLUE);
                Store_btn.setBackgroundColor(Color.BLUE);
                break;
            case "#FF00FF5D":
                removeButt.setBackgroundColor(Color.GREEN);
                Sites_btn.setBackgroundColor(Color.BLUE);
                Store_btn.setBackgroundColor(Color.BLUE);
                break;
            default:
                break;
        }
    }

}
