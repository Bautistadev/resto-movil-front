package com.example.resto.EntityDTO;

public class MesaDTO {


    private Long id;

    private String token;
    private boolean estado;
    private Long idEmpleado;

    public MesaDTO(Long id, String token, boolean estado, Long idEmpleado) {
        this.id = id;
        this.token = token;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
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

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "MesaDTO{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", estado=" + estado +
                ", idEmpleado=" + idEmpleado +
                '}';
    }
}
