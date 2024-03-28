package com.example.molbhav.ui.admin.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.molbhav.LoginActivity;
import com.example.molbhav.R;
import com.example.molbhav.databinding.FragmentAdminDashboardBinding;
import com.example.molbhav.databinding.FragmentAdminProfileBinding;
import com.example.molbhav.ui.admin.profile.AdminProfileViewModel;

public class AdminDashboardFragment extends Fragment {

    public static AdminDashboardFragment newInstance() {
        return new AdminDashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AdminDashboardViewModel adminDashboardViewModel =
                new ViewModelProvider(this).get(AdminDashboardViewModel.class);

        com.example.molbhav.databinding.FragmentAdminDashboardBinding binding = FragmentAdminDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView iconCatagoryManagement = binding.iconCatagoryManagement;

        iconCatagoryManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.nav_admin_catagory_management);

            }
        });



        return root;
    }



}