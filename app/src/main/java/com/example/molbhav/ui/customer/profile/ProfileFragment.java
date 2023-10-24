package com.example.molbhav.ui.customer.profile;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.molbhav.LoginActivity;
import com.example.molbhav.R;
import com.example.molbhav.databinding.FragmentHomeBinding;
import com.example.molbhav.databinding.FragmentProfileBinding;
import com.example.molbhav.service.TokenCheckService;
import com.example.molbhav.ui.customer.home.HomeViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = requireActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        final Button btnLogOut = binding.btnLogOut;

        btnLogOut.setOnClickListener(logOutClickListner);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private final View.OnClickListener logOutClickListner = v -> {

        redirectToLogin();

    };
    private void redirectToLogin() {
        // Clear token and expiration from shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText( requireActivity(), "Logged Out Successfully", Toast.LENGTH_SHORT ).show();

        // Redirect to the login activity
        Intent loginIntent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(loginIntent);
        if (getActivity() != null) {
            getActivity().finish();
            Intent serviceIntent = new Intent(getActivity(), TokenCheckService.class);
            getActivity().stopService(serviceIntent);
        }
    }

}