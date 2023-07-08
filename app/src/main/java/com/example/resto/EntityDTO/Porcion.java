package com.example.resto.EntityDTO;

import com.example.resto.Enum.porcionEnum;

public class Porcion {
    private Long id;

    private porcionEnum porcion;


    public Porcion() {
        super();
    }

    public Porcion(Long id, porcionEnum porcion) {
        super();
        this.id = id;
        this.porcion = porcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public porcionEnum getPorcion() {
        return porcion;
    }

    public void setPorcion(porcionEnum porcion) {
        this.porcion = porcion;
    }

    @Override
    public String toString() {
        return "Porcion [id=" + id + ", porcion=" + porcion + "]";
    }

}
