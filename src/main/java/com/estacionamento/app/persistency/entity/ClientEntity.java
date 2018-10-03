package com.estacionamento.app.persistency.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientEntity {

    private Long idClient;
    private String name;
    private String telephone;
    private String document;
    private List<VehicleEntity> vehicles;

    public ClientEntity() {
        vehicles = new ArrayList<>();
    }

    public ClientEntity(Long idClient, String name, String telephone, String document, List<VehicleEntity> vehicles) {
        this();
        this.idClient = idClient;
        this.name = name;
        this.telephone = telephone;
        this.document = document;
        this.vehicles = vehicles;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ClientEntity{"
                + "idClient = " + idClient
                + ", name = " + name
                + ", telephone = " + telephone
                + ", document = " + document
                + ", vehicles = " + vehicles.toString()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(getIdClient(), that.getIdClient()) &&
                getName().equals(that.getName()) &&
                getTelephone().equals(that.getTelephone()) &&
                getDocument().equals(that.getDocument())  &&
                Objects.equals(getVehicles(), that.getVehicles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdClient(), getName(), getTelephone(), getDocument(), getVehicles());
    }
}
