package com.estacionamento.app.persistency.entity;

import java.util.Objects;

public class ColorEntity {

    private Long idColor;
    private String color;

    public ColorEntity() {
    }

    public ColorEntity(Long idColor, String color) {
        this.idColor = idColor;
        this.color = color;
    }

    public Long getIdColor() {
        return idColor;
    }

    public void setIdColor(Long idColor) {
        this.idColor = idColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorEntity{"
                + "idColor=" + idColor
                + ", color='" + color + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorEntity)) return false;
        ColorEntity that = (ColorEntity) o;
        return Objects.equals(getIdColor(), that.getIdColor()) &&
                Objects.equals(getColor(), that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdColor(), getColor());
    }
}
