package com.estacionamento.app.view;

import com.estacionamento.app.controller.ClientController;
import com.estacionamento.app.controller.VehicleController;
import com.estacionamento.app.view.domain.ClientViewBean;
import com.estacionamento.app.view.domain.ColorViewBean;
import com.estacionamento.app.view.domain.SizeViewBean;
import com.estacionamento.app.view.domain.VehicleViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.ResourceBundle;

public class VehicleView extends View {

    private ResourceBundle bundle = ResourceBundle.getBundle("myLabels");

    private final VehicleController vehicleController;
    private final ClientController clientController;
    private final VehicleViewBean viewBean;
    private final ClientViewBean clientViewBean;

    public VehicleView(ClientController clientController, VehicleController vehicleController) {
        this.clientController = clientController;
        this.vehicleController = vehicleController;
        this.viewBean = new VehicleViewBean();
        this.clientViewBean = new ClientViewBean();
        this.viewBean.idClientProperty().bind(clientViewBean.idClientProperty());
    }

    public void loadData(Long idClient) {
        ClientViewBean client = clientController.findClientById(idClient);

        clientViewBean.setIdClient(client.getIdClient());
        clientViewBean.setName(client.getName());
        clientViewBean.setTelephone(client.getTelephone());
        clientViewBean.setDocument(client.getDocument());
    }

    public void loadVehicleData(Long idVehicle) {
        ClientViewBean client = clientController.findVehicleById(idVehicle);

        clientViewBean.setIdClient(client.getIdClient());
        clientViewBean.setName(client.getName());
        clientViewBean.setTelephone(client.getTelephone());
        clientViewBean.setDocument(client.getDocument());
    }

    public Scene buildScene() {
        TextField txModel = new TextField();
        txModel.textProperty().bindBidirectional(viewBean.modelProperty());
        TextField txPlate = new TextField();
        txPlate.textProperty().bindBidirectional(viewBean.plateProperty());

        Label lbSearchVehicle = new Label(bundle.getString("searchVehicle"));
        Label lbColor = new Label(bundle.getString("color"));
        Label lbModel = new Label(bundle.getString("model"));
        Label lbPlate = new Label(bundle.getString("plate"));
        Label lbSize = new Label(bundle.getString("size"));

        List<ColorViewBean> colors = vehicleController.findAllColors();
        ObservableList<ColorViewBean> colorObservableList =
                FXCollections.observableArrayList(colors);
        ComboBox<ColorViewBean> cbColor = new ComboBox<>();
        cbColor.setItems(colorObservableList);
        cbColor.getSelectionModel().selectFirst();
        cbColor.valueProperty().bindBidirectional(viewBean.colorProperty());

        List<SizeViewBean> sizes = vehicleController.findAllSizes();
        ObservableList<SizeViewBean> sizeObservableList =
                FXCollections.observableArrayList(sizes);
        ComboBox<SizeViewBean> cbSize = new ComboBox<>();
        cbSize.setItems(sizeObservableList);
        cbSize.getSelectionModel().selectFirst();
        cbSize.valueProperty().bindBidirectional(viewBean.sizeProperty());

        Label lbClient = new Label(bundle.getString("client"));
        Label lbName = new Label(bundle.getString("name"));
        Label lbDocument = new Label(bundle.getString("document"));
        Label lbTelephone = new Label(bundle.getString("telephone"));

        TextField txName = new TextField();
        txName.setEditable(false);
        txName.textProperty().bindBidirectional(clientViewBean.nameProperty());

        TextField txDocument = new TextField();
        txDocument.setEditable(false);
        txDocument.textProperty().bindBidirectional(clientViewBean.documentProperty());

        TextField txTelephone = new TextField();
        txTelephone.setEditable(false);
        txTelephone.textProperty().bindBidirectional(clientViewBean.telephoneProperty());

        Button btRegister = new Button(bundle.getString("register"));
        btRegister.setOnAction(e -> saveVehicle());

        Label mainTitle = new Label(bundle.getString("vehicleTitle"));
        BorderPane lytTitle = buildTitleLayout(mainTitle.getText());

        GridPane lytClient = new GridPane();
        lytClient.add(lbName, 0,0);
        lytClient.add(txName, 1,0);
        lytClient.add(lbDocument, 0,1);
        lytClient.add(txDocument, 1,1);
        lytClient.add(lbTelephone, 0,2);
        lytClient.add(txTelephone, 1,2);

        VBox lytClientSide = new VBox();
        lytClientSide.getChildren().addAll(lbClient, lytClient);

        GridPane lytVehicle = new GridPane();
        lytVehicle.add(lbPlate,0,0);
        lytVehicle.add(txPlate,1,0);
        lytVehicle.add(lbModel,0,1);
        lytVehicle.add(txModel,1,1);
        lytVehicle.add(lbColor,0,2);
        lytVehicle.add(cbColor,1,2);
        lytVehicle.add(lbSize,0,3);
        lytVehicle.add(cbSize,1,3);

        VBox lytVehicleSide = new VBox();
        lytVehicleSide.getChildren().addAll(lbSearchVehicle, lytVehicle);

        HBox lytCenter = new HBox();
        lytCenter.getChildren().addAll(lytClientSide, lytVehicleSide);

        BorderPane lytBottomButtons = buildBottomLine(bundle);

        VBox lytVehicleMain = new VBox();
        lytVehicleMain.getChildren().addAll(lytTitle, lytCenter, lytBottomButtons);

        return new Scene(lytVehicleMain, 600,600);
    }

    private void saveVehicle() {
        if (viewBean.getModel().isEmpty() || viewBean.getPlate().isEmpty()) {
            InfoBox.display(bundle.getString("vehicleNotSavedTitle"), bundle.getString("vehicleNotSavedMessage"));
        } else {
            vehicleController.saveVehicle(viewBean);
            clearFields();
        }
    }

    private BorderPane buildBottomLine(ResourceBundle bundle) {
        BorderPane pane = new BorderPane();
        HBox registerCancelButtons = new HBox();
        Button btRegister = new Button(bundle.getString("register"));
        btRegister.setOnAction(e -> saveVehicle());
        Button btclear = new Button(bundle.getString("clear"));
        btclear.setOnAction(e -> clearFields());

        registerCancelButtons.getChildren().addAll(btRegister, btclear);
        pane.setRight(registerCancelButtons);

        return pane;
    }

    private void clearFields() {
        viewBean.setIdVehicle(0);
        viewBean.setPlate("");
        viewBean.setModel("");
        viewBean.setColor(null);
        viewBean.setSize(null);
    }
}
