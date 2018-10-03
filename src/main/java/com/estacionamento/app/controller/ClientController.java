package com.estacionamento.app.controller;

import com.estacionamento.app.persistency.entity.ClientEntity;
import com.estacionamento.app.persistency.entity.ColorEntity;
import com.estacionamento.app.persistency.entity.SizeEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import com.estacionamento.app.service.ClientService;
import com.estacionamento.app.service.VehicleService;
import com.estacionamento.app.view.domain.ClientViewBean;
import com.estacionamento.app.view.domain.ColorViewBean;
import com.estacionamento.app.view.domain.SizeViewBean;
import com.estacionamento.app.view.domain.VehicleViewBean;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;
    private VehicleService vehicleService;

    public ClientController(ClientService clientService, VehicleService vehicleService) {
        this.clientService = clientService;
        this.vehicleService = vehicleService;
    }

    public List<VehicleEntity> findVehicles(String document) {
        return clientService.findVehicles(document);
    }

    public void saveClient(ClientViewBean viewBean) {
        ClientEntity entity = new ClientEntity();
        entity.setIdClient(viewBean.getIdClient());
        entity.setName(viewBean.getName());
        entity.setDocument(viewBean.getDocument());
        entity.setTelephone(viewBean.getTelephone());

        clientService.saveClient(entity);
    }

    public void registerClient(ClientViewBean viewBean) {
        ClientEntity entity = new ClientEntity();

        entity.setName(viewBean.getName());
        entity.setDocument(viewBean.getDocument());
        entity.setTelephone(viewBean.getTelephone());

        clientService.registerClient(entity);
    }

    public ClientViewBean findClientByDocument(String document) {
        ClientEntity entity = clientService.findClientByDocument(document);

        ClientViewBean clientViewBean = new ClientViewBean();

        clientViewBean.setIdClient(entity.getIdClient());
        clientViewBean.setDocument(entity.getDocument());
        clientViewBean.setName(entity.getName());
        clientViewBean.setTelephone(entity.getTelephone());

        List<VehicleViewBean> vehicleViewBeans = new ArrayList<>();
        for (VehicleEntity vehicleEntity : entity.getVehicles() ) {
            VehicleViewBean vehicleViewBean = new VehicleViewBean();

            vehicleViewBean.setIdVehicle(vehicleEntity.getIdVehicle());
            vehicleViewBean.setModel(vehicleEntity.getModel());

            ColorViewBean colorViewBean = colorEntityToColorViewBean(vehicleEntity.getColor());
            vehicleViewBean.setColor(colorViewBean);

            SizeViewBean sizeViewBean = sizeEntityToSizeViewBean(vehicleEntity.getSize());
            vehicleViewBean.setSize(sizeViewBean);

            vehicleViewBeans.add(vehicleViewBean);
        }
        clientViewBean.setVehicles(vehicleViewBeans);

        return clientViewBean;
    }

    private SizeViewBean sizeEntityToSizeViewBean(SizeEntity sizeEntity) {
        Long id = sizeEntity.getId();
        String size = sizeEntity.getSize();
        return new SizeViewBean(id, size);
    }

    private ColorViewBean colorEntityToColorViewBean(ColorEntity colorEntity) {
        Long idColor = colorEntity.getIdColor();
        String color = colorEntity.getColor();
        return new ColorViewBean(idColor, color);
    }

    public List<VehicleViewBean> findVehicle(String document) {
        List<VehicleEntity> vehicleEntityList = vehicleService.findVehicle(document);
        List<VehicleViewBean> viewBeanList = new ArrayList<>();

        for (VehicleEntity entity : vehicleEntityList ) {
            VehicleViewBean vehicleViewBean = new VehicleViewBean();

            vehicleViewBean.setIdVehicle(entity.getIdVehicle());
            vehicleViewBean.setModel(entity.getModel());
            vehicleViewBean.setColor(colorEntityToColorViewBean(entity.getColor()));
            vehicleViewBean.setSize(sizeEntityToSizeViewBean(entity.getSize()));

            viewBeanList.add(vehicleViewBean);
        }

        return viewBeanList;
    }

    public List<ClientViewBean> findAllClients() {
        List<ClientEntity> listClientEntity = clientService.findAllClients();
        List<ClientViewBean> listClientViewBean = new ArrayList<>();

        for ( ClientEntity entity : listClientEntity) {
            ClientViewBean viewBean = new ClientViewBean();

            viewBean.setIdClient(entity.getIdClient());
            viewBean.setName(entity.getName());
            viewBean.setDocument(entity.getDocument());
            viewBean.setTelephone(entity.getTelephone());

            for ( VehicleEntity vehicleEntity : entity.getVehicles() ) {
                VehicleViewBean bean = new VehicleViewBean();

                bean.setIdVehicle(vehicleEntity.getIdVehicle());
                bean.setPlate(vehicleEntity.getPlate());
                bean.setModel(vehicleEntity.getModel());
                bean.setColor(colorEntityToColorViewBean(vehicleEntity.getColor()));
                bean.setSize(sizeEntityToSizeViewBean(vehicleEntity.getSize()));

                viewBean.getVehicles().add(bean);
            }

            listClientViewBean.add(viewBean);
        }

        return listClientViewBean;
    }


    public List<ClientViewBean> findClientsByName(String name) {
        List<ClientViewBean> clientViewBeans = new ArrayList<>();

        List<ClientEntity> clientList = clientService.findClientByName(name);

        for ( ClientEntity entity : clientList) {
            ClientViewBean viewBean = new ClientViewBean();

            viewBean.setIdClient(entity.getIdClient());
            viewBean.setName(entity.getName());
            viewBean.setDocument(entity.getDocument());
            viewBean.setTelephone(entity.getTelephone());
            viewBean.setVehicles(new ArrayList<>());

            clientViewBeans.add(viewBean);
        }


        return clientViewBeans;
    }


    public ClientViewBean findClientById(Long id) {
        ClientEntity entity = clientService.findClientById(id);

        ClientViewBean viewBean = new ClientViewBean();

        viewBean.setIdClient(id);
        viewBean.setName(entity.getName());
        viewBean.setTelephone(entity.getTelephone());
        viewBean.setDocument(entity.getDocument());
        viewBean.setVehicles(new ArrayList<>());

        return viewBean;
    }

    public void updateClient(ClientViewBean client) {
        ClientEntity entity = new ClientEntity();

        entity.setName(client.getName());
        entity.setDocument(client.getName());
        entity.setTelephone(client.getTelephone());
        List<VehicleViewBean> vehicles = client.getVehicles();
        List<VehicleEntity> vehicleEntities = new ArrayList<>();
        for (VehicleViewBean viewBean : vehicles) {
            VehicleEntity vehicleEntity = new VehicleEntity(
                    viewBean.getIdVehicle(),
                    viewBean.getPlate(),
                    viewBean.getModel(),
                    new ColorEntity(viewBean.getColor().getId(),viewBean.getColor().getColor()),
                    new SizeEntity(viewBean.getSize().getId(), viewBean.getSize().getSize()),
                    viewBean.getIdClient()
            );
            vehicleEntities.add(vehicleEntity);
        }
        entity.setVehicles(vehicleEntities);

        clientService.updateClient(entity);
    }

    public void deleteClient(long idClient) {
        clientService.deleteClient(idClient);
    }

    public ClientViewBean loadClientsAndVehicles(long idClient) {
        ClientEntity entity = clientService.loadClientsAndVehicles(idClient);
        ClientViewBean clientViewBean = new ClientViewBean();

        clientViewBean.setIdClient(entity.getIdClient());
        clientViewBean.setName(entity.getName());
        clientViewBean.setDocument(entity.getDocument());
        clientViewBean.setTelephone(entity.getTelephone());

        for (VehicleEntity vehicleEntity : entity.getVehicles()) {
            VehicleViewBean vehicleViewBean = new VehicleViewBean();

            vehicleViewBean.setIdVehicle(vehicleEntity.getIdVehicle());
            vehicleViewBean.setPlate(vehicleEntity.getPlate());
            vehicleViewBean.setModel(vehicleEntity.getModel());
            vehicleViewBean.setColor(new ColorViewBean(
                    vehicleEntity.getColor().getIdColor(),
                    vehicleEntity.getColor().getColor())
            );
            vehicleViewBean.setSize(new SizeViewBean(
                    vehicleEntity.getSize().getId(),
                    vehicleEntity.getSize().getSize())
            );
            vehicleViewBean.setIdClient(vehicleEntity.getIdClient());
            clientViewBean.getVehicles().add(vehicleViewBean);
        }
        return clientViewBean;
    }

    public ClientViewBean findVehicleById(Long idVehicle) {
        return null;
    }
}
