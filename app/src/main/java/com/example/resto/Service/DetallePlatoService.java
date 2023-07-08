package com.example.resto.Service;

import com.example.resto.EntityDTO.DetallePlatoDTO;
import com.example.resto.EntityDTO.DetallePlatoRequestDTO;
import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.OcupacionRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DetallePlatoService {

    @POST("add")
    Call<DetallePlatoRequestDTO> save (@Body DetallePlatoRequestDTO detallePlato);

    @GET("retrive/{id}")
    Call<DetallePlatoDTO>retrive(@Path("id")Long id);

    @PUT("update")
    Call<DetallePlatoDTO> update(@Body DetallePlatoDTO detallePlato);

    @PUT("retriveAll")
    Call<List<DetallePlatoDTO>>retriveAll();
}
