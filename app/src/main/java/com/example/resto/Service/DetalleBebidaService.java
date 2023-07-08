package com.example.resto.Service;

import com.example.resto.EntityDTO.DetalleBebidaDTO;
import com.example.resto.EntityDTO.DetalleBebidaRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DetalleBebidaService {
    @POST("add")
    Call<DetalleBebidaRequestDTO> save (@Body DetalleBebidaRequestDTO detalleBebida);

    @GET("retrive/{id}")
    Call<DetalleBebidaDTO>retrive(@Path("id")Long id);

    @PUT("update")
    Call<DetalleBebidaDTO> update(@Body DetalleBebidaDTO detalleBebida);

    @PUT("retriveAll")
    Call<List<DetalleBebidaDTO>>retriveAll();
}
