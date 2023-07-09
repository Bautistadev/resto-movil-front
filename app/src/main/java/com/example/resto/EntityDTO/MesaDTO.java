package com.example.resto.EntityDTO;

public class MesaDTO {


    private Long id;

    private String token;
    private boolean estado;
    private EmpleadoDTO empleado;

    public MesaDTO(Long id, String token, boolean estado, EmpleadoDTO empleado) {
        this.id = id;
        this.token = token;
        this.estado = estado;
        this.empleado = empleado;
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

    @Override
    public String toString() {
        return "MesaDTO{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", estado=" + estado +
                '}';
    }
}
