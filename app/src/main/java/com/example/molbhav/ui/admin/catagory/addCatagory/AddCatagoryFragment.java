package com.example.molbhav.ui.admin.catagory.addCatagory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.molbhav.R;
import com.example.molbhav.api.ApiClient;
import com.example.molbhav.api.ApiService;
import com.example.molbhav.api.ResponseDTO;
import com.example.molbhav.databinding.FragmentAddCatagoryBinding;
import com.example.molbhav.databinding.FragmentCatagoryManagementBinding;
import com.example.molbhav.entity.ProductCategory;
import com.example.molbhav.ui.admin.catagory.catagoryManagement.CatagoryManagementFragment;
import com.example.molbhav.ui.admin.catagory.catagoryManagement.CatagoryManagementViewModel;
import com.example.molbhav.util.HelpingFunctions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCatagoryFragment extends Fragment {

    com.example.molbhav.databinding.FragmentAddCatagoryBinding binding;

    MaterialButton btnSubmit;
    ProgressBar progressBar;
    public static AddCatagoryFragment newInstance() {
        return new AddCatagoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AddCatagoryViewModel addCatagoryViewModel = new ViewModelProvider(this).get(AddCatagoryViewModel.class);

        binding = FragmentAddCatagoryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        // Set the title for the toolbar
        toolbar.setTitle("Add Category");
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
                NavController navController = NavHostFragment.findNavController(AddCatagoryFragment.this);
                navController.navigateUp();
            }
        });

        MaterialButton btnAddSubCatagory = binding.btnAddSubCatagory;
        LinearLayout subCategoryLayout = binding.subCategoryLayout;
        EditText edtCategory = binding.edtCategory;

        btnAddSubCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTextInputToLayout(subCategoryLayout);

            }
        });

        btnSubmit = binding.btnSubmit;
        progressBar = binding.progressBar;
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireActivity(), "Btn Clicked", Toast.LENGTH_SHORT).show();



                ProductCategory productCategory = new ProductCategory();

                List<ProductCategory> productCategoryList = new ArrayList<>();

                productCategory.setName(edtCategory.getText().toString());

                for (int i = 0; i < subCategoryLayout.getChildCount(); i++) {

                    View childView = subCategoryLayout.getChildAt(i);

                    if (childView instanceof LinearLayout){
                        LinearLayout linearLayout = (LinearLayout) childView;
                        View view = linearLayout.getChildAt(0);
                        if (view instanceof TextInputLayout) {
                            TextInputLayout textInputLayout = (TextInputLayout) view;
                            EditText textInputEditText = textInputLayout.getEditText();

                            if (textInputEditText != null) {
                                String userInput = textInputEditText.getText().toString();
                                ProductCategory subCatagory = new ProductCategory();
                                subCatagory.setName(userInput);
                                productCategoryList.add(subCatagory);
                            }
                        }
                    }
                }

                productCategory.setSubcategories(productCategoryList);

                Gson gson = new Gson();

                Log.d("Catagory",gson.toJson(productCategory));
                addCatagoryApi(productCategory);

            }
        });

        return root;
    }

    public void addTextInputToLayout(LinearLayout layout) {

        // Inflate the custom layout
        View customLayout = getLayoutInflater().inflate(R.layout.outlined_text_input_layout, null);

        // Add it to your existing layout (e.g., a parent LinearLayout)
        layout.addView(customLayout);

        // Find the delete button in the custom layout
        ImageView deleteButton = customLayout.findViewById(R.id.btnDeleteView);

        // Set a click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the customLayout when the delete button is clicked
                layout.removeView(customLayout);
            }
        });

    }

    private void addCatagoryApi(ProductCategory productCategory) {

        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.GONE);

        ApiClient apiClient = new ApiClient(requireActivity());
        ApiService apiService = apiClient.getApiClient().create(ApiService.class);

        Call<ResponseDTO<Object>> call = apiService.addProductCategory(productCategory);
        call.enqueue(new Callback<ResponseDTO<Object>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDTO<Object>> call, @NonNull Response<ResponseDTO<Object>> response) {

                Log.d("URL", response.raw().request().url().toString());

                progressBar.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    // Handle success
                    Log.d("Response Code", String.valueOf(response.code()));
                    assert response.body() != null;
                    ResponseDTO<Object> responseDTO = response.body();
                    Log.d("Response", responseDTO.toString());

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout for the AlertDialog
                    View customView = inflater.inflate(R.layout.success_dialoge, null);
                    // Build the AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setView(customView);
                    Button btnOk = customView.findViewById(R.id.btnOk);
                    // Create and show the AlertDialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            NavController navController = NavHostFragment.findNavController(AddCatagoryFragment.this);
                            navController.navigateUp();
                        }
                    });

                } else {
                    // Handle error
                    Log.d("Response Code", String.valueOf(response.code()));
//                    Log.d("Response Message", String.valueOf(response.body()));


                    try {
                        assert response.errorBody() != null;
                        Log.d("Response ErrorBody", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


//                    ResponseDTO<Object> responseDTO = response.errorBody();
                    Log.d("Response Message 2", String.valueOf(response.errorBody()));
                    Log.d("Response Code 1", String.valueOf(response));
//                    assert response.body() != null;
//                    ResponseDTO<Object> responseDTO = response.body();
//                    try {
//                        assert response.errorBody() != null;
//                        Log.d("Response", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    Toast.makeText(requireActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    HelpingFunctions.showAlert("Something Went wrong",requireActivity()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                        callAPI();
//                            Navigation.findNavController(requireView()).navigate(R.id.action_to_dashboard);
                        }
                    }).setCancelable(true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDTO<Object>> call, @NonNull Throwable t) {
                // Handle failure
                Log.e("API_CALL", "Error: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);
                HelpingFunctions.showAlert("Something Went wrong",requireActivity()).setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        callAPI();
//                        sendComplaint();
                    }
                }).setCancelable(true).show();
            }
        });


    }


}