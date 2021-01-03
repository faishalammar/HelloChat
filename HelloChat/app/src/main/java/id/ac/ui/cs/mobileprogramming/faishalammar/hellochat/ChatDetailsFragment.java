package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;


import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentChatDetailsBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.MessageViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service.MessageHandlerService;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.service.MessageServiceReceiver;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserViewModel;

import static id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.App.CHANNEL_1;


public class ChatDetailsFragment extends Fragment {

    private FragmentChatDetailsBinding binding;
    private FriendViewModel friendViewModel;
    private MessageViewModel messageViewModel;
    private UserViewModel userViewModel;
    private List<Message> listMessageReceived;
    private int friendSelectedId = 0;
    private String friendSelectedUsername;
    private MessageServiceReceiver messageServiceReceiver;

    public ChatDetailsFragment() {
    }

    public void setFriendSelectedId(int friendSelectedId) {
        this.friendSelectedId = friendSelectedId;
    }

    public void setFriendSelectedUsername(String friendSelectedUsername) {
        this.friendSelectedUsername = friendSelectedUsername;
    }


    public int getFriendSelectedId() {
        return friendSelectedId;
    }
    public String getFriendSelectedUsername() {
        return friendSelectedUsername;
    }


    @Override
    public void onStart(){
        super.onStart();
        messageServiceReceiver = new MessageServiceReceiver(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        IntentFilter filter = new IntentFilter();
//        filter.addAction(MessageServiceReceiver.ACTION_TRIGGER);
        MainActivity.getContextOfApplication().registerReceiver(messageServiceReceiver, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.getContextOfApplication().unregisterReceiver(messageServiceReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendViewModel = new ViewModelProvider(requireActivity()).get(FriendViewModel.class);
        messageViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        ChatDetailsAdapter adapter = new ChatDetailsAdapter();
        binding.listMessageRecyclerView.setAdapter(adapter);

        friendViewModel.getSelected().observe(getViewLifecycleOwner(), friendSelected -> {
            setFriendSelectedId(friendSelected.getId());
            setFriendSelectedUsername(friendSelected.getUsername());
            binding.username.setText(friendSelected.getUsername());
            adapter.setFriendSelectedId(friendSelected);
            adapter.setMessages(listMessageReceived);
        });

        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> listMessage) {
                listMessageReceived = listMessage;
                adapter.setMessages(listMessage);
            }
        });


        binding.sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = binding.editText.getText().toString();
                        Message newMessage = new Message(message, 4, getFriendSelectedId());
                        messageViewModel.insert(newMessage);

                        if(message.toLowerCase().contains("remind me in") && isForegroundPermissionApproved()){

                            String[] messageArr = message.split(" ");
                            for(int i = 0; i < messageArr.length; i++) {
                                String word = messageArr[i];

                                if(word.equals("me")){

                                    if(messageArr[i+1].equals("in")){
                                        int reminderTime = Integer.valueOf(messageArr[i+2]);
                                        startMessageService(reminderTime);
                                        replyMessage("Okayy I'll remind you later");
                                        Log.d("Reminder", String.valueOf(reminderTime));
                                        break;
                                    }
                                }
                            }
                        } else {
                            replyMessage(message);
                        }

                    }
                }
        );

    }

    public void replyMessage(String replyMessage){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // echo message replied
                Message repliedMessage = new Message(replyMessage , getFriendSelectedId(), 4);
                messageViewModel.insert(repliedMessage);
                sendMessageReceivedNotification(repliedMessage.getMessage(), getFriendSelectedUsername());
            }
        }, 2000);
    }
    public void sendMessageReceivedNotification(String receivedMessage, String senderName) {

        Intent activityIntent = new Intent(MainActivity.getContextOfApplication(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.getContextOfApplication(),
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(MainActivity.getContextOfApplication(), CHANNEL_1)
                .setSmallIcon(R.drawable.chat)
                .setContentTitle("New message from " + senderName)
                .setContentText(receivedMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = MainActivity.getNotificationManager();
        notificationManagerCompat.notify(1, notification);

    }
    public void startMessageService(int reminderTime) {
        Toast.makeText(MainActivity.getContextOfApplication(), "Start Service", Toast.LENGTH_LONG).show();
        Intent serviceIntent = new Intent(MainActivity.getContextOfApplication(), MessageHandlerService.class);
        serviceIntent.putExtra("timerCountdown", reminderTime);
        ContextCompat.startForegroundService(MainActivity.getContextOfApplication(), serviceIntent);
    }

    public Boolean isForegroundPermissionApproved(){
        Boolean status = false;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.FOREGROUND_SERVICE) == PackageManager.PERMISSION_GRANTED) {
            status = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.FOREGROUND_SERVICE},
                    1);
        }
        return status;
    }

    public void testFunction(){
        Log.d("Test", "tetst");
    }

}