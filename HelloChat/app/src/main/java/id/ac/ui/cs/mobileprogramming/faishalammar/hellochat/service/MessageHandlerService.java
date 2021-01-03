package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.MainActivity;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.R;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.MessageViewModel;

import static id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.App.CHANNEL_1;
import static id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.App.CHANNEL_2;

public class MessageHandlerService extends Service {

    private long timerRemaining;
    private String messageSent;
    private String receiverUsername;
    private MessageViewModel messageViewModel;
    private int countdownTime;
    private Intent messageIntent;
    private NotificationCompat.Builder notification;
    private NotificationManagerCompat notificationManager;
    public static String MY_TRIGGER = "trigger";


    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = MainActivity.getNotificationManager();
        notification = new NotificationCompat.Builder(this, CHANNEL_2)
                .setSmallIcon(R.drawable.chat)
                .setContentTitle("Reminder")
                .setContentText("timer is running on background")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(getResources().getColor(R.color.colorAlternativePink))
                .setAutoCancel(true);


        startForeground(2, notification.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        messageIntent = intent;
        countdownTime = intent.getIntExtra("timerCountdown", 0) * 1000;
        Log.d("check int extra ", String.valueOf(countdownTime));

        setTimer(countdownTime);
        return START_STICKY;
    }

    public void setTimer(int timerSecond){
        CountDownTimer countDownTimer = new CountDownTimer(timerSecond , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerRemaining = Math.round(millisUntilFinished/1000);
                Log.d("Timer : ", String.valueOf(timerRemaining));
                notification.setContentText("reminder time remaining : " + timerRemaining + " seconds");
                notification.setSound(null, AudioManager.STREAM_ALARM);
                notificationManager.notify(2, notification.build());
            }
            @Override
            public void onFinish() {
                Log.d("Timer : ", "Finish");
                Intent intentForSent = new Intent();

                notification.setContentText("Times Up");
                notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notification.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                notificationManager.notify(2, notification.build());

                intentForSent.putExtra("statusTimer","test timer");
//                intentForSent.setAction(MessageServiceReceiver.ACTION_TRIGGER);
                intentForSent.setComponent(new ComponentName(getPackageName(),"id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service.MessageServiceReceiver"));
                MainActivity.getContextOfApplication().sendBroadcast(intentForSent);

                onDestroy();
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
