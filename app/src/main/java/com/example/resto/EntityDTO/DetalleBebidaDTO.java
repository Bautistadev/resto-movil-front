package com.example.resto.EntityDTO;

public class DetalleBebidaDTO {

    private Long id;

    private Integer cantidad;

    private BebidaDTO bebida;

    private OcupacionDTO ocupacion;

    public DetalleBebidaDTO(Long id, Integer cantidad, BebidaDTO bebida, OcupacionDTO ocupacion) {
        this.id = id;
        this.cantidad = cantidad;
        this.bebida = bebida;
        this.ocupacion = ocupacion;
    }

    public DetalleBebidaDTO() {
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

    public BebidaDTO getBebida() {
        return bebida;
    }

    public void setBebida(BebidaDTO bebida) {
        this.bebida = bebida;
    }

    public OcupacionDTO getOcupacion() {
        return this.ocupacion;
    }

    public void setOcupacion(OcupacionDTO ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public String toString() {
        return "DetalleBebidaDTO{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", idBebida=" + bebida +
                ", idOcupacion=" + ocupacion +
                '}';
    }
}
