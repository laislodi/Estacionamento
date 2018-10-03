package com.estacionamento.app.view.domain;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehicleViewBean {

    private LongProperty idVehicle;
    private StringProperty plate;
    private StringProperty model;
    private SimpleObjectProperty<ColorViewBean> color;
    private SimpleObjectProperty<SizeViewBean> size;
    private SimpleLongProperty idClient;

    public VehicleViewBean() {
        this.idVehicle = new SimpleLongProperty();
        this.plate = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleObjectProperty<>();
        this.idClient = new SimpleLongProperty();
    }

    public long getIdVehicle() {
        return idVehicle.get();
    }

    public LongProperty idVehicleProperty() {
        return idVehicle;
    }

    public String getPlate() {
        return plate.get();
    }

    public StringProperty plateProperty() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate.set(plate);
    }

    public void setIdVehicle(long idVehicle) {
        this.idVehicle.set(idVehicle);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public ColorViewBean getColor() {
        return color.get();
    }

    public String getColorName() {
        return color.get().getColor();
    }

    public SimpleObjectProperty<ColorViewBean> colorProperty() {
        return color;
    }

    public void setColor(ColorViewBean color) {
        this.color.set(color);
    }

    public SizeViewBean getSize() {
        return size.get();
    }

    public String getSizeName() {
        return size.get().getSize();
    }

    public SimpleObjectProperty<SizeViewBean> sizeProperty() {
        return size;
    }

    public void setSize(SizeViewBean size) {
        this.size.set(size);
    }

    public long getIdClient() {
        return idClient.get();
    }

    public SimpleLongProperty idClientProperty() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient.set(idClient);
    }

    @Override
    public String toString() {
        return "VehicleViewBean{"
                + "idVehicle=" + idVehicle
                + ", plate=" + plate
                + ", model=" + model
                + ", color=" + color
                + ", size=" + size
                + ", idClient=" + idClient
                + '}';
    }
}
