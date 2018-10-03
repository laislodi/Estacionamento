package com.estacionamento.app.persistency.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class EntriesEntity {

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double paidValue;
    private Long idClient;
    private Long idVehicle;

    public EntriesEntity() {
    }

    public EntriesEntity(LocalDateTime entryTime, LocalDateTime exitTime, Double paidValue, Long idClient, Long idVehicle) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.paidValue = paidValue;
        this.idClient = idClient;
        this.idVehicle = idVehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    @Override
    public String toString() {
        return "EntriesEntity{"
                + "entryTime=" + entryTime
                + ", exitTime=" + exitTime
                + ", paidValue=" + paidValue
                + ", idClient=" + idClient
                + ", idVehicle=" + idVehicle
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntriesEntity)) return false;
        EntriesEntity that = (EntriesEntity) o;
        return Objects.equals(getEntryTime(), that.getEntryTime()) &&
                Objects.equals(getExitTime(), that.getExitTime()) &&
                Objects.equals(getPaidValue(), that.getPaidValue()) &&
                Objects.equals(getIdClient(), that.getIdClient()) &&
                Objects.equals(getIdVehicle(), that.getIdVehicle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getExitTime(), getPaidValue(), getIdClient(), getIdVehicle());
    }
}
