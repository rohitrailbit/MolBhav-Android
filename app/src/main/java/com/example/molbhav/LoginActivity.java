package com.example.molbhav;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    Button btnSignIn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(v -> {

            logIn(edtUsername.getText().toString(),edtPassword.getText().toString());

        });


    }

    public void logIn(String username,String password){

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

    public void goToMainActivity() {
        Intent intent = new Intent(this, com.example.molbhav.MainActivity.class);
        this.finish();
        startActivity(intent);
    }

}