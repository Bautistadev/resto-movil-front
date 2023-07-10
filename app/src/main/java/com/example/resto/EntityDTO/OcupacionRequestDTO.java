package com.example.resto.EntityDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OcupacionRequestDTO {


    private MesaDTO mesa;

    public OcupacionRequestDTO( MesaDTO mesa) {

        this.mesa = mesa;
    }

    public OcupacionRequestDTO() {
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "OcupacionDTO{" +

                ", mesa=" + mesa +
                '}';
    }
}
