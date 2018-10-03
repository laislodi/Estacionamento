package com.estacionamento.app.persistency.entity;

import java.util.Objects;

public class VehicleEntity {

    private Long idVehicle;
    private String plate;
    private String model;
    private ColorEntity color;
    private SizeEntity size;
    private Long idClient;

    public VehicleEntity(Long idVehicle, String plate, String model, ColorEntity color, SizeEntity size, Long idClient) {
        this.idVehicle = idVehicle;
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.size = size;
        this.idClient = idClient;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public String getColorName() {
        return color.getColor();
    }

    public void setColorName(String color) {
        this.color.setColor(color);
    }

    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "VehicleEntity {"
                + "idVehicle = " + idVehicle
                + ", plate = " + plate
                + ", model = " + model
                + ", color = " + color.getColor()
                + ", size = " + size.getSize()
                + ", idClient = " + idClient
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleEntity)) return false;
        VehicleEntity that = (VehicleEntity) o;
        return Objects.equals(getIdVehicle(), that.getIdVehicle()) &&
                getPlate().equals(that.getPlate()) &&
                getModel().equals(that.getModel()) &&
                getColor().equals(that.getColor()) &&
                getSize().equals(that.getSize()) &&
                getIdClient().equals(that.getIdClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVehicle(), getPlate(), getModel(), getColor(), getSize(), getIdClient());
    }
}
