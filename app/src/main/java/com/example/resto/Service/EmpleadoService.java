package com.example.resto.Service;

import com.example.resto.EntityDTO.ClienteDTO;
import com.example.resto.EntityDTO.EmpleadoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmpleadoService {
    @GET("retiveAll?_expand=rol")
    Call<List<EmpleadoDTO>> retriveAllEmpleado();

    @GET("retive/{id}?")
    Call<List<EmpleadoDTO>> retriveEmpleado(@Path("id") Long id);

}
