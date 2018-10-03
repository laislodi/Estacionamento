package com.estacionamento.app.view.domain;

import java.util.Objects;

public class ColorViewBean {

    private Long id;
    private String color;

    public ColorViewBean() {
    }

    public ColorViewBean(Long id, String color) {
        this.id = id;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizeViewBean)) return false;
        SizeViewBean that = (SizeViewBean) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getColor(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor());
    }
}
