package com.example.resto.Service;

import com.example.resto.EntityDTO.GeolocalizacionDTO;
import com.example.resto.EntityDTO.LoginDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GeolocalizacionService {

    @GET("retriveAll")
    Call<List<GeolocalizacionDTO>> retriveAll();
}
