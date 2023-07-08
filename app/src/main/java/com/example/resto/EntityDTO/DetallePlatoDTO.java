package com.example.resto.EntityDTO;

public class DetallePlatoDTO {
    private Long id;
    private Integer cantidad;
    private Long idPorcion;
    private Long idPlato;
    private Long idOcupacion;

    public DetallePlatoDTO(Integer cantidad, Long idPorcion, Long idPlato, Long idOcupacion) {
        this.id = id;
        this.cantidad = cantidad;
        this.idPorcion = idPorcion;
        this.idPlato = idPlato;
        this.idOcupacion = idOcupacion;
    }

    public DetallePlatoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdPorcion() {
        return idPorcion;
    }

    public void setIdPorcion(Long idPorcion) {
        this.idPorcion = idPorcion;
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    public Long getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Long idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    @Override
    public String toString() {
        return "DetallePlatoDTO{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", idPorcion=" + idPorcion +
                ", idPlato=" + idPlato +
                ", idOcupacion=" + idOcupacion +
                '}';
    }
}
