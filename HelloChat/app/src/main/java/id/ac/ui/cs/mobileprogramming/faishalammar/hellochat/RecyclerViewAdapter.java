package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentChatDetailsBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentChatDetailsBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Message> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public RecyclerViewAdapter(List<Message> list) {
        this.list = list;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Message getItemAt(int index){
        return list.get(index);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        
        FragmentChatDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_chat_history, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.binding.date.setText(list.get(position).getDate());
        holder.binding.username.setText(list.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FragmentChatDetailsBinding binding;

        public ViewHolder(@NonNull FragmentChatDetailsBinding binding){
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
