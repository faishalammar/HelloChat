package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
    public static Boolean isConnected(Context context) {
        String status = null;
        Boolean networkIsConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            status = "Internet is Available";
            networkIsConnected = true;
            return networkIsConnected;
        } else {
            status = "No internet is available";
            networkIsConnected = false;
            return networkIsConnected;
        }
    }
}



