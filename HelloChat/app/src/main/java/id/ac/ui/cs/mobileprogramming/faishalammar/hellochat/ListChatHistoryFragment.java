package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentListChatHistoryBinding;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.Friend;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.friend.FriendViewModel;

import java.util.List;
import java.util.Locale;


public class ListChatHistoryFragment extends Fragment {
    private FragmentListChatHistoryBinding binding;
    private ChatDetailsFragment chatDetailsFragment = new ChatDetailsFragment();
    private ListContactFragment listContactFragment = new ListContactFragment();
    private Locale locale;

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

        locale = getResources().getConfiguration().locale;
        Log.d("checkLocale", locale.getLanguage());

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

        Context context = getActivity().getApplicationContext();
        NetworkStatus networkStatus = new NetworkStatus();

        adapter.setListener((v, position) -> {
            if(!networkStatus.isConnected(context)){
                Toast.makeText(context, "Unstable Connection. Please Check Your Connection and Try Again", Toast.LENGTH_LONG).show();
            } else {
                if(MainActivity.isTablet(getContext())){
                    friendViewModel.setSelected(adapter.getItemAt(position));
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.container_2, chatDetailsFragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    friendViewModel.setSelected(adapter.getItemAt(position));
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.container, chatDetailsFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        binding.contactButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.container, listContactFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        );

    }
}
