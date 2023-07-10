package com.example.resto.EntityDTO;

public class DetallePlatoRequestDTO {
    private Integer cantidad = 0;
    private Porcion porcion;
    private PlatoDTO plato;
    private OcupacionDTO ocupacion;

    public DetallePlatoRequestDTO(Integer cantidad, Porcion porcion, PlatoDTO plato, OcupacionDTO ocupacion) {

        this.cantidad = cantidad;
        this.porcion = porcion;
        this.plato = plato;
        this.ocupacion = ocupacion;
    }

    public DetallePlatoRequestDTO() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Porcion getPorcion() {
        return porcion;
    }

    public void setPorcion(Porcion porcion) {
        this.porcion = porcion;
    }

    public PlatoDTO getPlato() {
        return plato;
    }

    public void setIdPlato(PlatoDTO plato) {
        this.plato = plato;
    }

    public OcupacionDTO getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionDTO ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void AumentarCantidad(){
        this.cantidad = cantidad+1;
    }


    @Override
    public String toString() {
        return "DetallePlatoRequestDTO{" +
                "cantidad=" + cantidad +
                ", porcion=" + porcion +
                ", plato=" + plato +
                ", ocupacion=" + ocupacion +
                '}';
    }
}
