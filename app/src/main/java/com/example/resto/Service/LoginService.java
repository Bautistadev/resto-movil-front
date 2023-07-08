package com.example.resto.Service;

import com.example.resto.EntityDTO.LoginDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<LoginDTO> validateSeccion(@Body LoginDTO login);
}
