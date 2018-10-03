package com.estacionamento.app.service;

import com.estacionamento.app.persistency.ClientDao;
import com.estacionamento.app.persistency.entity.ClientEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<VehicleEntity> findVehicles(String document) {
        return clientDao.findVehicles(document);
    }

    public void saveClient(ClientEntity entity) {
        clientDao.saveClient(entity);
    }

    public void registerClient(ClientEntity entity) {
        clientDao.registerClient(entity);
    }

    public ClientEntity findClientByDocument(String document) {
        return clientDao.findClientByDocument(document);
    }

    public List<ClientEntity> findAllClients() {
        return clientDao.findAllClients();
    }

    public ClientEntity findClientById(Long id) {
        return clientDao.findClientById(id);
    }

    public List<ClientEntity> findClientByName(String name) {
        return clientDao.findClientByName(name);
    }

    public void updateClient(ClientEntity entity) {
        clientDao.updateClient(entity);
    }

    public void deleteClient(Long idClient) {
        clientDao.deleteClient(idClient);
    }

    public ClientEntity loadClientsAndVehicles(long idClient) {
        return clientDao.loadClientAndVehicles(idClient);
    }
}
