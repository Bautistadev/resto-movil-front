package com.example.resto.EntityDTO;

import java.util.Objects;

public class MesaDTO {


    private Long id;

    private String token;
    private boolean estado;
    private EmpleadoDTO empleado;
    private GeolocalizacionDTO geolocalizacion;

    public MesaDTO(Long id, String token, boolean estado, EmpleadoDTO empleado,GeolocalizacionDTO geolocalizacion) {
        this.id = id;
        this.token = token;
        this.estado = estado;
        this.empleado = empleado;
        this.geolocalizacion = geolocalizacion;
    }

    public MesaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public GeolocalizacionDTO getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(GeolocalizacionDTO geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MesaDTO)) return false;
        MesaDTO mesaDTO = (MesaDTO) o;
        return estado == mesaDTO.estado && Objects.equals(id, mesaDTO.id) && Objects.equals(token, mesaDTO.token) && Objects.equals(empleado, mesaDTO.empleado) && Objects.equals(geolocalizacion, mesaDTO.geolocalizacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, estado, empleado, geolocalizacion);
    }

    @Override
    public String toString() {
        return "MesaDTO{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", estado=" + estado +
                ", empleado=" + empleado +
                ", geolocalizacion=" + geolocalizacion +
                '}';
    }
}
