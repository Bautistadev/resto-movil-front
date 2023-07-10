package com.example.resto.Utils;

import com.example.resto.Service.*;

public class Apis {
    //CREAMOS URLS
    public static final String urlMarca = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Marca/";
    public static final String urlPersona = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Cliente/";

    public static final String urlEmpleado = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Empleado/";
    public static final String urlLogin = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/security/";
    public static final String urlPlato = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Plato/";
    public static final String urlPlatoDetalle = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/DetallePlatoController/";
    public static final String urlBebidaDetalle = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/DetalleBebidaController/";
    public static final String urlGeolocalizacion = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Geolocalizacion/";
    public static final String urlMesa = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Mesa/";
    public static final String urlOcupacion = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Ocupacion/";

    //ACCEDIMOS A LA URL QUE REPRESENTA A UN SERVICIO WEB
    public static MarcaService getMarcaService(){
        return  RetrofitControl.getRetrofit(urlMarca).create(MarcaService.class);
    }
    public static EmpleadoService getEmpleadoService(){
        return RetrofitControl.getRetrofit(urlEmpleado).create(EmpleadoService.class);
    }
    public static ClienteService getClienteService(){
        return RetrofitControl.getRetrofit(urlPersona).create(ClienteService.class);
    }
    public static LoginService getLoginService(){
        return RetrofitControl.getRetrofit(urlLogin).create(LoginService.class);
    }
    public static PlatoService getPlatoService(){
        return RetrofitControl.getRetrofit(urlPlato).create(PlatoService.class);
    }
    public static BebidaService BebidaService(){
        return RetrofitControl.getRetrofit(urlPlato).create(BebidaService.class);
    }
    public static DetallePlatoService getDetallePlatoService(){
        return RetrofitControl.getRetrofit(urlPlatoDetalle).create(DetallePlatoService.class);
    }
    public static DetalleBebidaService getDetalleBebidaService(){
        return RetrofitControl.getRetrofit(urlBebidaDetalle).create(DetalleBebidaService.class);
    }
    public static GeolocalizacionService getGeolocalizacionService(){
        return RetrofitControl.getRetrofit(urlGeolocalizacion).create(GeolocalizacionService.class);
    }
    public static MesaService getMesaService(){
        return RetrofitControl.getRetrofit(urlMesa).create(MesaService.class);
    }
    public static OcupacionService getOcupacionService(){
        return RetrofitControl.getRetrofit(urlOcupacion).create(OcupacionService.class);
    }

    public static  MesaService updateMesa(){
        return RetrofitControl.getRetrofit(urlMesa).create(MesaService.class);
    }



}
