package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserViewModel;


public class MainActivity extends AppCompatActivity {

    private ListChatHistoryFragment listChatHistoryFragment = new ListChatHistoryFragment();
    private ChatDetailsFragment chatDetailsFragment = new ChatDetailsFragment();
    private NetworkStatus networkStatus;
    private BroadcastReceiver networkReceiver = null;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkReceiver = new NetworkConnectionReceiver();
        contextOfApplication = getApplicationContext();

        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if(isLandscape(getApplicationContext())){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listChatHistoryFragment)
                    .addToBackStack(null)
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_2, chatDetailsFragment)
                    .addToBackStack(null)
                    .commit();

        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listChatHistoryFragment)
                    .addToBackStack("list_chat_history")
                    .commit();

        }

    }



    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }



    public static boolean isPhoneAndLandscape(Context context){
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        int screenOrientation = context.getResources().getConfiguration().orientation;

        return (screenLayout >= Configuration.SCREENLAYOUT_SIZE_SMALL & 
                screenLayout <= Configuration.SCREENLAYOUT_SIZE_LARGE & 
                screenOrientation == Configuration.ORIENTATION_LANDSCAPE);
            
    }

}



