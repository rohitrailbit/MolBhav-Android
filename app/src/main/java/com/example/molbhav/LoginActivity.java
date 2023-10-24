package com.example.molbhav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.molbhav.api.ApiClient;
import com.example.molbhav.api.ApiService;
import com.example.molbhav.api.AuthResponse;
import com.example.molbhav.api.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    EditText edtUsername,edtPassword;
    Button btnSignIn;
    SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(v -> {

            logIn();

        });


    }

    public void staticLogIn(String username,String password){

        if (username.equalsIgnoreCase("rohit")){
            if (password.equalsIgnoreCase("rohit")){
                goToMainActivity();
            } else{
                edtPassword.setError("Incorrect Password");
            }

        } else {
            edtUsername.setError("Incorrect Username");
        }

    }

    public void logIn(){

        if (edtUsername.getText().toString().isEmpty()){
            edtUsername.setError("Required");
            edtUsername.requestFocus();
            return;

        } else if (edtPassword.getText().toString().isEmpty()){
            edtPassword.setError("Required");
            edtPassword.requestFocus();
            return;
        }

        String userName = edtUsername.getText().toString();
        String passWord = edtPassword.getText().toString();

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();


        // Create the API service
        ApiClient apiClient = new ApiClient(this);
        ApiService apiService = apiClient.getAuthApiClient().create(ApiService.class);

        // Create the LoginRequest object with the required data
        LoginRequest loginRequest = new LoginRequest(userName.trim(), passWord);

        // Make the API call
        Call<AuthResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
//                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    // API call successful, handle the response here
                    // Handle the response data as needed
                    AuthResponse authResponse = response.body();

                    Log.d("Response Code", String.valueOf(response.code()));
                    Log.d("Response Body", response.body().toString());
                    assert authResponse != null;
                    Log.d("Response Token ",  authResponse.getToken());
                    Log.d("User Details ",  authResponse.getUser().toString());

                    String userId = authResponse.getUser().getId();
                    String userType = authResponse.getUser().getRole().getName();
                    String RoleId = authResponse.getUser().getRole().getId();

                    Log.d("userType", userType);
                    Log.d("userId", String.valueOf(userId));
                    Log.d("usertype", userType);
                    Log.d("userTypeId", String.valueOf(RoleId));

                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//
                    SharedPreferences.Editor login_edit = sharedPreferences.edit();
                    login_edit.putBoolean("is_logged", true);
                    login_edit.putString("userId", userId);
                    login_edit.putString("userTypeId", RoleId);
                    login_edit.putString("userType", userType);
                    login_edit.putString("name", authResponse.getUser().getName());
                    login_edit.putString("mobile", authResponse.getUser().getContact());
                    login_edit.putString("authToken", authResponse.getToken());
                    login_edit.apply();

                    goToMainActivity();

                } else {
                    // API call failed, handle the error here
                    // You can check response.code() for the HTTP status code
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        Log.d("Response ErrorBody", errorBody);
                        Log.d("Response Code", String.valueOf(response.code()));
                        // Parse the string as a JSON object
                        Log.d("New   ", errorBody);
                        JSONObject jObjError = new JSONObject(errorBody);
                        Toast.makeText(LoginActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
//                        Toast.makeText(LoginActivity.this, "" + message.getMessage(), Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                // API call failed due to network issues or other errors
                // Handle the failure here
//                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                Log.e("API_CALL", "Error: " + t.getMessage());

            }
        });


    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, com.example.molbhav.MainActivity.class);
        this.finish();
        startActivity(intent);
    }

}