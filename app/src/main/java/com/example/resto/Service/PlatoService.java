package com.example.resto.Service;

import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.OcupacionRequestDTO;
import com.example.resto.EntityDTO.PlatoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlatoService {

    @GET("retrivePlato/{id}")
    Call<PlatoDTO>retrive(@Path("id")Long id);

    @PUT("updatePlato")
    Call<PlatoDTO> update(@Body PlatoDTO ocupacion);

    @GET("retriveAllPlato")
    Call<List<PlatoDTO>>retriveAllPlato();
}
