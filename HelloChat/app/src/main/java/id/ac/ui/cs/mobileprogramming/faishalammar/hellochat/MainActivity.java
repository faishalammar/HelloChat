package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.User;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserViewModel;


public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private ListChatHistoryFragment listChatHistoryFragment = new ListChatHistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Update Content
            }
        });


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, listChatHistoryFragment)
                .addToBackStack("chat_history")
                .commit();
    }
}

