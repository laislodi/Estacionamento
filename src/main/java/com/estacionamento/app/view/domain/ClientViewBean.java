package com.estacionamento.app.view.domain;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ClientViewBean {

    private SimpleLongProperty idClient;
    private StringProperty name;
    private StringProperty telephone;
    private StringProperty document;
    private final ObservableList<VehicleViewBean> vehicles;

    public ClientViewBean() {
        this.idClient = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.telephone = new SimpleStringProperty();
        this.document = new SimpleStringProperty();
        this.vehicles = FXCollections.observableArrayList();
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getDocument() {
        return document.get();
    }

    public StringProperty documentProperty() {
        return document;
    }

    public void setDocument(String document) {
        this.document.set(document);
    }

    public ObservableList<VehicleViewBean> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleViewBean> vehicles) {
        this.vehicles.setAll(vehicles);
    }

    @Override
    public String toString() {
        return "ClientViewBean{"
                + "idClient=" + idClient
                + ", name=" + name
                + ", telephone=" + telephone
                + ", document=" + document
                + ", vehicles=" + vehicles.toString()
                + '}';
    }
}
