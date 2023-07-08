package com.example.resto.EntityDTO;

import com.example.resto.Enum.enumRol;

public class Rol {

    private Long id;

    private enumRol rol;

    public Rol(Long id, enumRol rol) {
        this.id = id;
        this.rol = rol;
    }

    public Rol() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enumRol getRol() {
        return rol;
    }

    public void setRol(enumRol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", rol=" + rol +
                '}';
    }
}
