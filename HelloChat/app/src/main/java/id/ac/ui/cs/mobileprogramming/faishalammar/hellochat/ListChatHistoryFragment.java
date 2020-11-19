package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentListChatHistoryBinding;

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
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        List<Message> list = new ArrayList<>();

        list.add(new Message("Username User", "pesan terakhir yang perlu dibalas", "Finish lab 4 Mobile Programming Course"));
        list.add(new Message("Faishal Ammar", "pesan terakhir yang dikirimkan", "Learn more about Playcanvas"));
        list.add(new Message("John Doe", "pesan terakhir yang perlu dibalas", "Complete Sherlock Holmes Series"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        binding.recyclerView.setAdapter(adapter);

        adapter.setListener((v, position) -> {
            viewModel.setSelected(adapter.getItemAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, chatDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });


    }
}
