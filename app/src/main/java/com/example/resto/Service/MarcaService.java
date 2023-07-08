package com.example.resto.Service;

import com.example.resto.EntityDTO.MarcaDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//EN ESTA CLASE, VAMOS A IMPLEMENTAR LOS EMTODOS PARA CONSUMIR DE LA WEB
public interface MarcaService {

    @GET("retrive/{id}")
    Call<MarcaDTO> retriveMarca(@Path("id") Long MarcaId);

    @GET("retriveAll")
    Call<List<MarcaDTO>> retriveAllMarca();
}
