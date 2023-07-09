package com.example.resto.Service;

import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.OcupacionRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MesaService {

    @GET("retrive/{id}?_expand=empleado")
    Call<MesaDTO>retrive(@Path("id")Long id);

    @PUT("update?_expand=empleado")
    Call<MesaDTO> update(@Body MesaDTO mesa);

    @GET("retriveAll?_expand=empleado")
    Call<List<MesaDTO>>retriveAll();

    @GET("retriveMesaByToken/{token}")
    Call<MesaDTO> retriveMesaByToken(@Path("token") String token);
}
