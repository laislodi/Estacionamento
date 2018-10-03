package com.estacionamento.app.view;

import com.estacionamento.app.controller.ClientController;
import com.estacionamento.app.controller.VehicleController;
import com.estacionamento.app.view.domain.ClientViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class VehicleListView {

    private VehicleController vehicleController;
    private ClientController clientController;
    private ResourceBundle bundle = ResourceBundle.getBundle("myLabels");

    private Button btSearchVehicle;
    private TableView<ClientViewBean> tbVehicleViewBean;
    private Button btClear;

    public VehicleListView(ClientController clientController, VehicleController vehicleController) {
        this.clientController = clientController;
        this.vehicleController = vehicleController;
    }

    public Scene buildVehicleRegisterScene() {
        Stage window = new Stage();
        window.setTitle(bundle.getString("vehicleTitle"));
        window.setMinHeight(1000);
        window.setMinHeight(700);
        window.initModality(Modality.APPLICATION_MODAL);

        Label lbSearchVehicle = new Label(bundle.getString("searchVehicle"));

        ObservableList<String> stringObservableList =
                FXCollections.observableArrayList(
                        bundle.getString("plate"),
                        bundle.getString("name"),
                        bundle.getString("document"),
                        bundle.getString("telefone"),
                        bundle.getString("color"),
                        bundle.getString("model"),
                        bundle.getString("size")
                        );
        ComboBox<String> cbFilter = new ComboBox<>(stringObservableList);
        cbFilter.getSelectionModel().selectFirst();
        TextField txFilter = new TextField();

        Button btRegisterVehicle = new Button(bundle.getString("register"));
        btRegisterVehicle.setOnAction(e -> {
            VehicleView vehicleView = new VehicleView(clientController, vehicleController);
            Scene scene = vehicleView.buildScene();

            window.setScene(scene);
            window.showAndWait();
        });

        Button btEdit = new Button(bundle.getString("edit"));
        btEdit.setOnAction(e -> {

        });

        return null;
    }
}
