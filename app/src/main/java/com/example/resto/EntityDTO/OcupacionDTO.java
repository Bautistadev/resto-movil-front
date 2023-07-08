package com.example.resto.EntityDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OcupacionDTO {

    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private List<DetallePlatoDTO> detallePlato;
    private List<DetalleBebidaDTO> detalleBebida;

    private MesaDTO mesa;

    public OcupacionDTO(Long id,LocalDateTime inicio, LocalDateTime fin, List<DetallePlatoDTO> detallePlato, List<DetalleBebidaDTO> detalleBebida,MesaDTO mesa) {
        this.inicio = inicio;
        this.fin = fin;
        this.detallePlato = detallePlato;
        this.detalleBebida = detalleBebida;
        this.mesa = mesa;
        this.id = id;
    }

    public OcupacionDTO() {
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public List<DetallePlatoDTO> getDetallePlato() {
        return detallePlato;
    }

    public void setDetallePlato(List<DetallePlatoDTO> detallePlato) {
        this.detallePlato = detallePlato;
    }

    public List<DetalleBebidaDTO> getDetalleBebida() {
        return detalleBebida;
    }

    public void setDetalleBebida(List<DetalleBebidaDTO> detalleBebida) {
        this.detalleBebida = detalleBebida;
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
                "id=" + id +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", detallePlato=" + detallePlato +
                ", detalleBebida=" + detalleBebida +
                ", mesa=" + mesa +
                '}';
    }
}
