package com.estacionamento.app.view.domain;

import java.util.Objects;

public class SizeViewBean {
    private Long id;
    private String size;

    public SizeViewBean() {
    }

    public SizeViewBean(Long id, String size) {
        this.id = id;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizeViewBean)) return false;
        SizeViewBean that = (SizeViewBean) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize());
    }
}
