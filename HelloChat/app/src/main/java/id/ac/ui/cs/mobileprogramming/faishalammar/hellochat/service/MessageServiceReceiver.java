package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.ChatDetailsFragment;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.MainActivity;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.R;

import static id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.App.CHANNEL_1;

public class MessageServiceReceiver extends BroadcastReceiver {
    public static final String ACTION_TRIGGER = "id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service.MyBroadcastReceiver.ACTION_TRIGGER";

    ChatDetailsFragment chatDetailsFragment;

    public MessageServiceReceiver() {
    }

    public MessageServiceReceiver(ChatDetailsFragment fragment){
        chatDetailsFragment = fragment;
        String friends = "aaa";
        if(chatDetailsFragment.getFriendSelectedUsername() != null){
            friends = chatDetailsFragment.getFriendSelectedUsername();
        }
        Log.d("Receiver : username ", friends);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedMessage = intent.getStringExtra("statusTimer");
        sendMessageReceivedNotification("Reminder Time's Up", "Don't forget your agenda");

//        chatDetailsFragment.testFunction();
    }


    public void sendMessageReceivedNotification(String receivedMessage, String Title) {

        Intent activityIntent = new Intent(MainActivity.getContextOfApplication(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.getContextOfApplication(),
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(MainActivity.getContextOfApplication(), CHANNEL_1)
                .setSmallIcon(R.drawable.chat)
                .setContentTitle(Title)
                .setContentText(receivedMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = MainActivity.getNotificationManager();
        notificationManagerCompat.notify(1, notification);

    }


}
