package com.example.resto.EntityDTO;

import com.example.resto.EntityDTO.OcupacionDTO;

public class ClienteRequestDTO {
    private OcupacionDTO ocupacion;
    private String nombre;
    private String apellido;

    public ClienteRequestDTO(Long id, OcupacionDTO ocupacion,String nombre, String apellido) {

        this.ocupacion = ocupacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public ClienteRequestDTO() {
    }

    public OcupacionDTO getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionDTO ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
