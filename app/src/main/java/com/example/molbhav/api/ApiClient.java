package com.example.molbhav.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.molbhav.LoginActivity;
import com.example.molbhav.deserializer.LocalDateDeserializer;
import com.example.molbhav.deserializer.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    public static final String BASE_URL = "https://tricareservice.onrender.com";
//    public static final String BASE_URL = "http://3.110.83.143:9001";
    public static final String BASE_URL = "https://fc77-103-55-61-64.ngrok-free.app";

    private Retrofit retrofit;

    public String authToken;
    SharedPreferences sharedPreferences;

    public ApiClient(Activity context){
        sharedPreferences = context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        authToken = sharedPreferences.getString("authToken","");
        Log.d("Token", authToken);
    }



    public Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .setLenient()
            .create();

    public Retrofit getAuthApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();
        }
        return retrofit;
    }

    public Retrofit getApiClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(authToken))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}

