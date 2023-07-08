package com.example.resto.EntityDTO;

import java.util.Date;

public class PlatoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Date dateCreated;
    private Date dateDeleted;
    private Categoria categoria;


    public PlatoDTO(Long id,String nombre, String descripcion,
                     Date dateCreated, Date dateDeleted,Categoria categoria, Float precio) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dateCreated = dateCreated;
        this.dateDeleted = dateDeleted;
        this.categoria = categoria;
        this.precio = precio;
    }

    public PlatoDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "PlatoDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
                + ", dateCreated=" + dateCreated + ", dateDeleted=" + dateDeleted + ", categoria=" + categoria + "]";
    }

}
