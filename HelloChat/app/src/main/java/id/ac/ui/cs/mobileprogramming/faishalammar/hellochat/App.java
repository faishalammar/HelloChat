package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1 = "channelMessage";
    public static final String CHANNEL_2 = "channelTimer";

    @Override
    public void onCreate(){
        super.onCreate();

        // crete notification channel when app created
        createNewNotificationChannel();
    }

    private void createNewNotificationChannel(){
        // Start from oreo we need to make notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel messageNotificationChannel = new NotificationChannel(
                    CHANNEL_1,
                    "message notification channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationChannel timerNotificationChannel = new NotificationChannel(
                    CHANNEL_2,
                    "timer notification channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(messageNotificationChannel);
            notificationManager.createNotificationChannel(timerNotificationChannel);

        }
    }
}
