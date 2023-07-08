package com.example.resto.EntityDTO;

import com.example.resto.Enum.CategoriaEnum;

public class Categoria {
    private Long id;

    private CategoriaEnum categoria;

    public Categoria(Long id,CategoriaEnum categoria) {
        super();
        this.id = id;
        this.categoria = categoria;
    }

    public Categoria() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", categoria=" + categoria + "]";
    }

}
