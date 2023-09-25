package com.example.molbhav.api;


import com.example.molbhav.entity.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/login/")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/user/{id}")
    Call<ResponseDTO<User>> getUserById(@Path("id") Integer id);



}
