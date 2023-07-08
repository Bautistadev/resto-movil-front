package com.example.resto.EntityDTO;

public class DetalleBebidaRequestDTO {

    private Integer cantidad;

    private BebidaDTO bebida;

    private OcupacionDTO ocupacion;

    public DetalleBebidaRequestDTO( Integer cantidad, BebidaDTO bebida, OcupacionDTO ocupacion) {

        this.cantidad = cantidad;
        this.bebida = bebida;
        this.ocupacion = ocupacion;
    }

    public DetalleBebidaRequestDTO() {
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
        return ocupacion;
    }

    public void setOcupacion(OcupacionDTO ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public String toString() {
        return "DetalleBebidaDTO{" +
                ", cantidad=" + cantidad +
                ", idBebida=" + bebida +
                ", idOcupacion=" + ocupacion +
                '}';
    }
}
