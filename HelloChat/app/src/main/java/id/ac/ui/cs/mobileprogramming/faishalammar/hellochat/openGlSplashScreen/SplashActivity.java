package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.openGlSplashScreen;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.MainActivity;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.R;

public class SplashActivity extends AppCompatActivity {

    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (detectOpenGLES30()) {
            setContentView(new OpenGLView(this));
            openGLView = (OpenGLView) findViewById(R.id.openGlView);
        } else {
            Log.e("openglcube", "OpenGL ES 3.0 not supported on device.  Exiting...");
            finish();

        }



        // Create Thread that will sleep for 5 seconds
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent intent =new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }

    private boolean detectOpenGLES30() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        Log.d("openGlVersion", Integer.toString(info.reqGlEsVersion));

        return (info.reqGlEsVersion >= 0x20000);
    }

}
