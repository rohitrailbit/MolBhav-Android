package com.example.molbhav.ui.customer.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.molbhav.R;
import com.example.molbhav.adapters.ImageSliderAdapter;
import com.example.molbhav.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;

public class CustomerDashboard extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;

        ViewPager viewPager = binding.viewPager;
        List<Integer> imageList = Arrays.asList(
                R.drawable.sample1,
                R.drawable.sample2,
                R.drawable.sample3,
                R.drawable.sample4
        );
        ImageSliderAdapter adapter = new ImageSliderAdapter(requireActivity(), imageList);
        viewPager.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}