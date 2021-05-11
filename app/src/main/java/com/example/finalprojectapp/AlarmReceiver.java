package com.example.finalprojectapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String NAME = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(NAME, "Inside onReceive");
        //Initializing the Notification Manager
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Calls the helper
        deliverNotification(context);
        Log.d(NAME, "Leaving onReceive");
    }
    private void deliverNotification(Context context){
        Log.d(NAME, "Inside helper");
        //Initialize the intent to receive it from MainActivity
        Intent contentIntent = new Intent(context, MainActivity.class);
        //Receiving the intent
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Set up the Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentTitle("Need List Alert")
                .setContentText("This is to remind you of the items you need or want")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        Log.d(NAME, "Leaving Helper");
    }
}
