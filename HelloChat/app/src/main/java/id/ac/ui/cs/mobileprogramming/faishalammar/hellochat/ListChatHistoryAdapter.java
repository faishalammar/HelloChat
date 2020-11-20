package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.ChatHistoryBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;


public class ListChatHistoryAdapter extends RecyclerView.Adapter<ListChatHistoryAdapter.FriendsHolder> {

    private List<Friend> listFriends = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public FriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ChatHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_history, parent, false);
        return new FriendsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsHolder holder, int position){
        holder.binding.lastMessage.setText(String.valueOf(listFriends.get(position).getPhoneNumber()));
        holder.binding.username.setText(listFriends.get(position).getUsername());
//        holder.binding.lastMessage.setText(listFriends.get(position).getMessageId());
//        holder.binding.username.setText(listFriends.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return listFriends.size();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ListChatHistoryAdapter() {
    }

    public ListChatHistoryAdapter(List<Friend> list) {
        this.listFriends = list;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setFriends(List<Friend> friends) {
        this.listFriends = friends;
        notifyDataSetChanged();
    }
    
    public Friend getItemAt(int index){
        return listFriends.get(index);
    }
    
    public class FriendsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ChatHistoryBinding binding;

        public FriendsHolder(@NonNull ChatHistoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            listener.onClick(v, getAdapterPosition());
        }

    }

}
