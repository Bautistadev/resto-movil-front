package com.example.resto.EntityDTO;

import java.util.Date;

public class BebidaDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private Date dateCreated;

    private Date dateDeleted;

    private MarcaDTO marca;

    private Float precio;

    public BebidaDTO(Long id, String nombre, String descripcion, Date dateCreated, Date dateDeleted, MarcaDTO marca, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dateCreated = dateCreated;
        this.dateDeleted = dateDeleted;
        this.marca = marca;
        this.precio = precio;
    }

    public BebidaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public MarcaDTO getMarca() {
        return marca;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public void setIdMarca(MarcaDTO marca) {
        this.marca = marca;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "BebidaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateDeleted=" + dateDeleted +
                ", Marca=" + marca +
                ", precio=" + precio +
                '}';
    }
}
