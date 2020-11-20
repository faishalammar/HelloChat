package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentListChatHistoryBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListChatHistoryFragment extends Fragment {

    private FragmentListChatHistoryBinding binding;
    private ChatDetailsFragment chatDetailsFragment = new ChatDetailsFragment();

    public ListChatHistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_chat_history, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FriendViewModel friendViewModel;
        friendViewModel = new ViewModelProvider(requireActivity()).get(FriendViewModel.class);

        ListChatHistoryAdapter adapter = new ListChatHistoryAdapter();
        binding.listFriendsRecyclerView.setAdapter(adapter);

        friendViewModel.getAllFriends().observe(this, new Observer<List<Friend>>() {
            @Override
            public void onChanged(@Nullable List<Friend> friends) {
                adapter.setFriends(friends);
            }
        });
        
        adapter.setListener((v, position) -> {
            friendViewModel.setSelected(adapter.getItemAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, chatDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });


    }
}
