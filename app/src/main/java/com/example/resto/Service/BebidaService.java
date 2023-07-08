package com.example.resto.Service;

import com.example.resto.EntityDTO.BebidaDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BebidaService {

    @GET("retriveAllBebida")
    Call<List<BebidaDTO>> retriveAllCliente();

    @GET("retriveBebida/{id}")
    Call<BebidaDTO> retriveBebida();


}
