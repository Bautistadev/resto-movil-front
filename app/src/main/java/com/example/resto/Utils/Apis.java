package com.example.resto.Utils;

import com.example.resto.Service.ClienteService;
import com.example.resto.Service.EmpleadoService;
import com.example.resto.Service.MarcaService;

public class Apis {
    //CREAMOS URLS
    public static final String urlMarca = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Marca/";
    public static final String urlPersona = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Cliente/";
    public static final String urlEmpleado = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Empleado/";

    //ACCEDIMOS A LA URL QUE REPRESENTA A UN SERVICIO WEB
    public static MarcaService getMarcaService(){
        return  RetrofitControl.getRetrofit(urlMarca).create(MarcaService.class);
    }
    public static ClienteService getClienteService(){
        return RetrofitControl.getRetrofit(urlPersona).create(ClienteService.class);
    }

    public static EmpleadoService getEmpleadoService(){
        return RetrofitControl.getRetrofit(urlEmpleado).create(EmpleadoService.class);
    }

}
