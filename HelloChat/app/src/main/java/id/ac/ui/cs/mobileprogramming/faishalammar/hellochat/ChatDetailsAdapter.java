package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.MessageRepliedBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.MessageSentBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.message.Message;


public class ChatDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class RepliedMessageHolder extends RecyclerView.ViewHolder{

        private MessageRepliedBinding binding;

        public RepliedMessageHolder(@NonNull MessageRepliedBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }


    }
    public class SentMessageHolder extends RecyclerView.ViewHolder{

        private MessageSentBinding binding;

        public SentMessageHolder(@NonNull MessageSentBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }


    }

    private List<Message> listMessage = new ArrayList<>();
    private Friend friendSelected;

    private OnItemClickListener listener;


    @Override
    public int getItemViewType(int position) {
        int output = 0;
        boolean isFriendReceiveMessage = false;
        boolean isFriendSentMessage  = false;

        Message currentMessage = listMessage.get(position);

        if (friendSelected!=null){
            if(currentMessage.getUserReceiverId() == friendSelected.getId()){
                isFriendReceiveMessage = true;
                return 1;
            } else if (currentMessage.getUserSenderId() == friendSelected.getId()){
                isFriendSentMessage= true;
                return 2;
            }
        }
        return output;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        MessageRepliedBinding messageRepliedBinding = DataBindingUtil.inflate(inflater, R.layout.message_replied, parent, false);
        MessageSentBinding messageSentBinding = DataBindingUtil.inflate(inflater, R.layout.message_sent, parent, false);

        switch (viewType) {
            case 1:
                return new RepliedMessageHolder(messageRepliedBinding);
            case 2:
                return new SentMessageHolder(messageSentBinding);
        }
        RepliedMessageHolder emptyHolder = new RepliedMessageHolder(messageRepliedBinding);
        LinearLayout layout = emptyHolder.binding.containerLayout;
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = 0;
        layout.setLayoutParams(params);
        return emptyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        switch (holder.getItemViewType()) {
            case 1:
                RepliedMessageHolder repliedMessageHolder = (RepliedMessageHolder) holder;
                repliedMessageHolder.binding.message.setText(String.valueOf(listMessage.get(position).getMessage()));
                repliedMessageHolder.binding.username.setText(String.valueOf(friendSelected.getUsername()));
                break;

            case 2:
                SentMessageHolder sentMessageHolder = (SentMessageHolder) holder;
                sentMessageHolder.binding.message.setText(String.valueOf(listMessage.get(position).getMessage()));
                sentMessageHolder.binding.username.setText(String.valueOf(friendSelected.getUsername()));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ChatDetailsAdapter() {
    }

    public ChatDetailsAdapter(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setMessages(List<Message> listMessage) {
        this.listMessage = listMessage;
        notifyDataSetChanged();
    }

    public void setFriendSelectedId(Friend friend){
        this.friendSelected = friend;
    }
    
    public Message getItemAt(int index){
        return listMessage.get(index);
    }
    


}
