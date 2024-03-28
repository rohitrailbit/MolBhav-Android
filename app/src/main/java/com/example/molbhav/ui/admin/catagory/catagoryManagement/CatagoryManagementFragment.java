package com.example.molbhav.ui.admin.catagory.catagoryManagement;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.molbhav.R;
import com.example.molbhav.adapters.CategoryAdapter;
import com.example.molbhav.api.ApiClient;
import com.example.molbhav.api.ApiService;
import com.example.molbhav.api.ResponseDTO;
import com.example.molbhav.databinding.FragmentAdminDashboardBinding;
import com.example.molbhav.databinding.FragmentCatagoryManagementBinding;
import com.example.molbhav.entity.ProductCategory;
import com.example.molbhav.ui.admin.home.AdminDashboardViewModel;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagoryManagementFragment extends Fragment {

    com.example.molbhav.databinding.FragmentCatagoryManagementBinding binding;

    public static CatagoryManagementFragment newInstance() {
        return new CatagoryManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CatagoryManagementViewModel catagoryManagementViewModel =
                new ViewModelProvider(this).get(CatagoryManagementViewModel.class);

        binding = FragmentCatagoryManagementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        // Set the title for the toolbar
        toolbar.setTitle("Category Management");
        // Set the fragment options menu to have the toolbar as the action bar
        setHasOptionsMenu(true);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        // Enable "Up" or "Back" navigation with the back arrow
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        // Handle the navigation when the back arrow is clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the previous destination
                NavController navController = NavHostFragment.findNavController(CatagoryManagementFragment.this);
                navController.navigateUp();
            }
        });

        MaterialButton btnAddCatagory = binding.btnAddCatagory;

        btnAddCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.nav_admin_add_catagory);

            }
        });

        callAPI();

        return root;
    }

    public void callAPI(){

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiClient apiClient = new ApiClient(requireActivity());
        ApiService apiService = apiClient.getApiClient().create(ApiService.class);

        Call<ResponseDTO<List<ProductCategory>>> call = apiService.getAllProductCategory();

        call.enqueue(new Callback<ResponseDTO<List<ProductCategory>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDTO<List<ProductCategory>>> call, @NonNull Response<ResponseDTO<List<ProductCategory>>> response) {
                progressDialog.dismiss();
//                Log.d("Token", authToken);
                if (response.isSuccessful()) {
                    // API call successful, handle the response here
                    // Handle the response data as needed

//                    Log.d("Response Code", String.valueOf(response.code()));
//                    assert response.body() != null;
//                    Log.d("Response Body", String.valueOf(response.body()));
                    ResponseDTO<List<ProductCategory>> responseDTO = response.body();
                    assert responseDTO != null;
                    List<ProductCategory> productCategoryList = responseDTO.getData();

                    // Find the RecyclerView in your fragment's layout
                    RecyclerView recyclerView = binding.categoryRecyclerView;

                    // Create a GridLayoutManager with the desired number of columns (4 in this case)
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
                    recyclerView.setLayoutManager(layoutManager);

                    // Create an adapter and set it to the RecyclerView
                    CategoryAdapter adapter = new CategoryAdapter(productCategoryList);
                    recyclerView.setAdapter(adapter);



                } else {
                    // API call failed, handle the error here
                    // You can check response.code() for the HTTP status code
                    Log.d("Response Body", String.valueOf(response.body()));
                    try {
                        assert response.errorBody() != null;
                        Log.d("Response ErrorBody", response.errorBody().string());
                        Log.d("Response Code", String.valueOf(response.code()));
                        String errorBody = response.errorBody().string();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDTO<List<ProductCategory>>> call, @NonNull Throwable t) {
                // API call failed due to network issues or other errors
                // Handle the failure here
                progressDialog.dismiss();
//                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                Log.e("API_CALL", "Error: " + t.getMessage());
            }
        });

    }


}