package com.example.finalprojectapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

public class ListActivity extends AppCompatActivity {
    private Button gotBtn;
    private Button needBtn;
    private final String NAME = "ListActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(NAME, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        gotBtn = findViewById(R.id.got_button);
        needBtn = findViewById(R.id.need_button);
        gotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(NAME, "Inside this method");
                Intent intent = new Intent(v.getContext(), GotActivity.class);
                startActivity(intent);
                Log.d(NAME, "Leaving this method");
            }
        });
        needBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NeedActivity.class);
                startActivity(intent);
            }
        });
        //notification button
        Button createNotificationsButton = findViewById(R.id.button_listactivity);

        createNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(NAME, "Inside Notification onClick");
                // intializes the function below
                addNotifications();
            }
        });
        //Include the following 2 in NeedActivity and GotActivity
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Store SharedPreference value here so that it goes into the helper
        loadBCFromPref(sharedPreferences);
        Log.d(NAME, "Leaving onCreate");
    }

    private void addNotifications() {
        Log.d(NAME, "Inside addNotifications");
        // Building the notifications
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Final Project Notifications")
                .setContentText("An item has just been added, check it out ")
                .setContentText("Hypebeast shoes has been added to your shopping list");

        // Creates the intent needed to show the notifications
        Intent notificationIntent = new Intent(this, ListActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        //Add as a Notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
        Log.d(NAME, "Leaving addNotifications");



    }
    //These are the helpers used to change the background colors of the buttons
    //Since you want to change the buttons in Got and Need, make these helpers in
    //NeedActivity and GotActivity
    private void loadBCFromPref(SharedPreferences sharedPreferences){
        changeBC(sharedPreferences.getString(getString(R.string.color_choices),"#0000FF"));
    }
    private void changeBC(String color){
        switch (color){
            case "#0000FF":
                gotBtn.setBackgroundColor(Color.BLUE);
                needBtn.setBackgroundColor(Color.BLUE);
                break;
            case "#FFFF0059":
                gotBtn.setBackgroundColor(Color.RED);
                needBtn.setBackgroundColor(Color.RED);
                break;
            case "#FF00FF5D":
                gotBtn.setBackgroundColor(Color.GREEN);
                needBtn.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }
}
