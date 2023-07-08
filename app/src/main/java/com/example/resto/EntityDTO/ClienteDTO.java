package com.example.resto.EntityDTO;

public class ClienteDTO {

    private Long id;
    private OcupacionDTO ocupacion;
    private String nombre;
    private String apellido;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, OcupacionDTO ocupacion, String nombre, String apellido) {
        this.id = id;
        this.ocupacion = ocupacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OcupacionDTO getIdocupacion() {
        return ocupacion;
    }

    public void setIdocupacion(Long idocupacion) {
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

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", idocupacion=" + ocupacion +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
