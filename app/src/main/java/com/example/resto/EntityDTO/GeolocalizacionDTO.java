package com.example.resto.EntityDTO;

public class GeolocalizacionDTO {


    private Long id;


    private Float x1;

    private Float x2;

    private Float x3;

    private Float x4;


    private Float y1;

    private Float y2;

    private Float y3;

    private Float y4;

    public GeolocalizacionDTO(Long id, Float x1, Float x2, Float x3, Float x4, Float y1, Float y2, Float y3, Float y4) {
        this.id = id;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
    }

    public GeolocalizacionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getX1() {
        return x1;
    }

    public void setX1(Float x1) {
        this.x1 = x1;
    }

    public Float getX2() {
        return x2;
    }

    public void setX2(Float x2) {
        this.x2 = x2;
    }

    public Float getX3() {
        return x3;
    }

    public void setX3(Float x3) {
        this.x3 = x3;
    }

    public Float getX4() {
        return x4;
    }

    public void setX4(Float x4) {
        this.x4 = x4;
    }

    public Float getY1() {
        return y1;
    }

    public void setY1(Float y1) {
        this.y1 = y1;
    }

    public Float getY2() {
        return y2;
    }

    public void setY2(Float y2) {
        this.y2 = y2;
    }

    public Float getY3() {
        return y3;
    }

    public void setY3(Float y3) {
        this.y3 = y3;
    }

    public Float getY4() {
        return y4;
    }

    public void setY4(Float y4) {
        this.y4 = y4;
    }
    public boolean estaDentroDelArea(Float x,Float y) {
        // Verificar si el punto está dentro del área utilizando la fórmula del rectángulo


        double minX = Math.min(this.x1, Math.min(this.x2, Math.min(this.x3, this.x4)));
        double maxX = Math.max(this.x1, Math.max(this.x2, Math.max(this.x3, this.x4)));
        double minY = Math.min(this.y1, Math.min(this.y2, Math.min(this.y3, this.y4)));
        double maxY = Math.max(this.y1, Math.max(this.y2, Math.max(this.y3, this.y4)));

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    @Override
    public String toString() {
        return "GeolocalizacionDTO{" +
                "id=" + id +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", y3=" + y3 +
                ", y4=" + y4 +
                '}';
    }
}
