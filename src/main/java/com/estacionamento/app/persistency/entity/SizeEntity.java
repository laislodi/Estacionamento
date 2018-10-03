package com.estacionamento.app.persistency.entity;

import java.util.Objects;

public class SizeEntity {
    private Long id;
    private String size;

    public SizeEntity() {
    }

    public SizeEntity(Long id, String size) {
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
        return "SizeEntity{"
                + "id=" + id
                + ", size='" + size + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizeEntity)) return false;
        SizeEntity that = (SizeEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize());
    }
}
