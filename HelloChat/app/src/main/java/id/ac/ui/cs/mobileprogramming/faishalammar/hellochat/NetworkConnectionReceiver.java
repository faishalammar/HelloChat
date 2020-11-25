package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkStatus networkStatus = new NetworkStatus();
        if(!networkStatus.isConnected(context)){
            Toast.makeText(context, "your internet is disconnected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "you're back to online", Toast.LENGTH_LONG).show();
        }
    }
}
