package com.estacionamento.app.service;

import com.estacionamento.app.persistency.VehicleDao;
import com.estacionamento.app.persistency.entity.ColorEntity;
import com.estacionamento.app.persistency.entity.SizeEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public List<VehicleEntity> findVehicle(String document) {
        return vehicleDao.findVehicle(document);
    }

    public List<Long> findClientsByPlate(String plate) {
        return vehicleDao.findIdClientsByPlate(plate);
    }

    public List<ColorEntity> findAllColors() {
        return vehicleDao.findAllColors();
    }

    public List<SizeEntity> findAllSizes() {
        return vehicleDao.findAllSizes();
    }

    public void saveVehicle(VehicleEntity entity) {
        vehicleDao.saveVehicle(entity);
    }
}
