package com.example.resto.Service;

import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.OcupacionRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OcupacionService {

    @POST("add")
    Call<OcupacionRequestDTO> save (@Body OcupacionRequestDTO ocupacion);

    @GET("retrive/{id}")
    Call<OcupacionDTO>retrive(@Path("id")Long ocupacion);

    @PUT("update")
    Call<OcupacionDTO> update(@Body OcupacionDTO ocupacion);

    @PUT("retriveAll")
    Call<List<OcupacionDTO>>retriveAll();

}
