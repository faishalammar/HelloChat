package id.ac.ui.cs.mobileprogramming.faishalammar.hellochat;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.faishalammar.hellochat.databinding.FragmentChatDetailsBinding;


public class ChatDetailsFragment extends Fragment {

    private FragmentChatDetailsBinding binding;

    public ChatDetailsFragment() {
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
//        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
//            binding.date.setText(item.getDate());
//            binding.username.setText(item.getUsername());
//            binding.details.setText(item.getDetails());
//        });
    }
}
