package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentChatDetailsBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.MessageViewModel;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.user.UserViewModel;


public class ChatDetailsFragment extends Fragment {

    private FragmentChatDetailsBinding binding;

    List<Message> listMessageReceived;
    int friendSelectedId = 0;

    public ChatDetailsFragment() {
    }

    public void setFriendSelectedId(int friendSelectedId) {
        this.friendSelectedId = friendSelectedId;
    }

    public int getFriendSelectedId() {
        return friendSelectedId;
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
        FriendViewModel friendViewModel;
        MessageViewModel messageViewModel;
        UserViewModel userViewModel;

        friendViewModel = new ViewModelProvider(requireActivity()).get(FriendViewModel.class);
        messageViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        ChatDetailsAdapter adapter = new ChatDetailsAdapter();
        binding.listMessageRecyclerView.setAdapter(adapter);

        friendViewModel.getSelected().observe(getViewLifecycleOwner(), friendSelected -> {
            setFriendSelectedId(friendSelected.getId());
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


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // echo message replied
                                Message repliedMessage = new Message(message, getFriendSelectedId(), 4);
                                messageViewModel.insert(repliedMessage);
                                messageViewModel.insert(repliedMessage);
                            }
                        }, 5000);



                    }
                }
        );

    }




}