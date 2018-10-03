package com.estacionamento.app.controller;

import com.estacionamento.app.persistency.entity.ColorEntity;
import com.estacionamento.app.persistency.entity.SizeEntity;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import com.estacionamento.app.service.VehicleService;
import com.estacionamento.app.view.domain.ColorViewBean;
import com.estacionamento.app.view.domain.SizeViewBean;
import com.estacionamento.app.view.domain.VehicleViewBean;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VehicleController {

    private VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    public List<Long> findIdClientsByPlate(String plate) {
        return service.findClientsByPlate(plate);
    }


    public List<ColorViewBean> findAllColors() {
        List<ColorViewBean> colorViewBeans = new ArrayList<>();
        List<ColorEntity> colorEntities = service.findAllColors();

        for (ColorEntity entity : colorEntities ) {
            ColorViewBean viewBean = new ColorViewBean();
            viewBean.setId(entity.getIdColor());
            viewBean.setColor(entity.getColor());
            colorViewBeans.add(viewBean);
        }

        return colorViewBeans;
    }

    public List<SizeViewBean> findAllSizes() {
        List<SizeViewBean> sizeViewBeans = new ArrayList<>();
        List<SizeEntity> sizeEntities = service.findAllSizes();

        for (SizeEntity entity : sizeEntities ) {
            SizeViewBean viewBean = new SizeViewBean();
            viewBean.setId(entity.getId());
            viewBean.setSize(entity.getSize());
            sizeViewBeans.add(viewBean);
        }

        return sizeViewBeans;
    }

    public void saveVehicle(VehicleViewBean viewBean) {
        VehicleEntity entity = new VehicleEntity(
                viewBean.getIdVehicle(),
                viewBean.getPlate(),
                viewBean.getModel(),
                new ColorEntity(viewBean.getColor().getId(),viewBean.getColor().getColor()),
                new SizeEntity(viewBean.getSize().getId(),viewBean.getSize().getSize()),
                viewBean.getIdClient()
        );

        service.saveVehicle(entity);
    }
}
