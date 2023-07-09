package com.example.resto.Service;

import com.example.resto.EntityDTO.LoginDTO;
import com.example.resto.EntityDTO.TokenObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<TokenObject> validateSeccion(@Body LoginDTO login);
}
