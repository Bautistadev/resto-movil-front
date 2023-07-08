package com.example.resto.Service;

import com.example.resto.EntityDTO.ClienteDTO;
import com.example.resto.EntityDTO.ClienteRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClienteService {

    @GET("retrive/{id}")
    Call<ClienteDTO> retriveCliente(@Path("id") Long ClienteId);

    @GET("retriveAll")
    Call<List<ClienteDTO>> retriveAllCliente();

    @POST("add")
    Call<ClienteRequestDTO> save(@Body ClienteRequestDTO cliente);

}