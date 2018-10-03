package com.estacionamento.app.view;

import com.estacionamento.app.controller.ClientController;
import com.estacionamento.app.persistency.entity.VehicleEntity;
import com.estacionamento.app.view.domain.ClientViewBean;
import com.estacionamento.app.view.domain.VehicleViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;

public class ClientView extends View {

    private final ClientController clientController;
    private VehicleView vehicleView;
    private final ClientViewBean viewBean;

    private final ResourceBundle bundle = ResourceBundle.getBundle("myLabels");
    private Stage vehicleWindow;

    public ClientView(ClientController clientController, VehicleView vehicleView) {
        this.clientController = clientController;
        this.vehicleView = vehicleView;
        this.viewBean = new ClientViewBean();
    }

    public void loadData(Long idClient) {
        ClientViewBean client = clientController.loadClientsAndVehicles(idClient);

        viewBean.setIdClient(client.getIdClient());
        viewBean.setName(client.getName());
        viewBean.setDocument(client.getDocument());
        viewBean.setTelephone(client.getTelephone());
        viewBean.setVehicles(client.getVehicles());
    }

    public Scene buildClientScene() {
        vehicleWindow = new Stage();
        vehicleWindow.setTitle(bundle.getString("vehicleTitle"));
        vehicleWindow.setMinWidth(600);
        vehicleWindow.setMinHeight(500);
        vehicleWindow.initModality(Modality.APPLICATION_MODAL);
        vehicleWindow.setScene(vehicleView.buildScene());

        TextField txName = new TextField();
        txName.textProperty().bindBidirectional(viewBean.nameProperty());
        TextField txDocument = new TextField();
        txDocument.textProperty().bindBidirectional(viewBean.documentProperty());
        TextField txTelephone = new TextField();
        txTelephone.textProperty().bindBidirectional(viewBean.telephoneProperty());

        Label lbName = new Label(bundle.getString("name"));
        Label lbDocument = new Label(bundle.getString("document"));
        Label lbTelephone = new Label(bundle.getString("telephone"));

        // main layout - It contains everything
        VBox lytClientMain = new VBox();
        lytClientMain.setPadding(new Insets(10,10,10,10)); // borders

        // First Line - It contains the title in a horizon Box that contains a label with the title and a vertical Box with the number of parking spots and the real time
        Label mainTitle = new Label();
        mainTitle.setText(bundle.getString("clientTitle"));
        BorderPane lytTitle = buildTitleLayout(mainTitle.getText());

        // Second line - It contains all of the scene, except the bottom buttons
        // Two columns: Clients and vehicles
        HBox lytCenter = new HBox();
        lytCenter.setPadding(new Insets(10,10,10,10)); // borders
        // Column of Clients
        Label clientTitle = new Label(bundle.getString("client"));
        VBox lytClientSide = new VBox();
        GridPane lytClient = new GridPane();
        // Client details, with 3 lines and 3 columns: name, document and telephone
        lytClient.add(lbName,0,0);
        lytClient.add(lbDocument,0,1);
        lytClient.add(lbTelephone,0,2);
        lytClient.add(txName,1,0);
        lytClient.add(txDocument,1,1);
        lytClient.add(txTelephone,1,2);

        lytClientSide.getChildren().addAll(clientTitle,lytClient);
        // Vehicle Side
        VBox lytVehicleSide = buildVehicleSide(bundle);
        // End of Second Line
        lytCenter.getChildren().addAll(lytClientSide,lytVehicleSide);

        Scene clientScene = new Scene(lytClientMain,1000,700);

        // Third Line - It contains the clear and exit buttons
        BorderPane lytBottomButtons = buildBottomLine(bundle);

        // Setting the Main Layout
        lytClientMain.getChildren().addAll(lytTitle, lytCenter, lytBottomButtons);

        return clientScene;
    }

    private VBox buildVehicleSide(ResourceBundle bundle) {
        VBox vehiclePane = new VBox();
        vehiclePane.setPadding(new Insets(10,10,10,10));

        Label lbVehicle = new Label(bundle.getString("vehicle"));
        TableView<VehicleViewBean> vehicleTable = new TableView<>();

        TableColumn<VehicleViewBean, String> plateColumn = new TableColumn<>(bundle.getString("plate"));
        plateColumn.setMinWidth(120);
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));

        TableColumn<VehicleViewBean, String> modelColumn = new TableColumn<>(bundle.getString("model"));
        modelColumn.setMinWidth(120);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<VehicleViewBean, String> colorColumn = new TableColumn<>(bundle.getString("color"));
        colorColumn.setMinWidth(120);
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("colorName"));

        TableColumn<VehicleViewBean, String> sizeColumn = new TableColumn<>(bundle.getString("size"));
        sizeColumn.setMinWidth(120);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("sizeName"));

        vehicleTable.getColumns().addAll(plateColumn,modelColumn,colorColumn,sizeColumn);
        vehicleTable.setItems(viewBean.getVehicles());

        Button btEditVehicle = new Button(bundle.getString("btNewVehicle"));
        btEditVehicle.setOnAction(e -> {
            vehicleView.loadVehicleData(vehicleTable.getSelectionModel().getSelectedItem().getIdVehicle());
            vehicleWindow.showAndWait();
        });
        vehiclePane.getChildren().addAll(lbVehicle, vehicleTable);

        return vehiclePane;
    }

    private ObservableList<VehicleEntity> findVehicles(String document) {
        ObservableList<VehicleEntity> vehicles = FXCollections.observableArrayList();

        List<VehicleEntity> vehicleEntities = clientController.findVehicles(document);
        vehicles.addAll(vehicleEntities);

        return vehicles;
    }

    private BorderPane buildBottomLine (ResourceBundle bundle) {
        BorderPane pane = new BorderPane();
        HBox registerCancelButtons = new HBox();
        Button btRegister = new Button(bundle.getString("register"));
        btRegister.setOnAction(e -> {
            if (viewBean.getIdClient() == 0) {
                registerClient();
            } else {
                saveClient();
            }
        });

        registerCancelButtons.getChildren().addAll(btRegister);
        pane.setRight(registerCancelButtons);

        return pane;
    }

    private void registerClient() {
        clientController.registerClient(viewBean);
        clearScene();
    }

    private ClientViewBean findClientByDocument(String document) {
        return clientController.findClientByDocument(document);
    }

    private void saveClient() {
        if (viewBean.getDocument().isEmpty() || viewBean.getName().isEmpty() || viewBean.getTelephone().isEmpty()) {
            InfoBox.display(bundle.getString("clientNotSavedTitle"),"clientNotSavedMessage");
        } else {
            clientController.saveClient(viewBean);
            clearScene();

        }
    }

    private void clearScene() {
        viewBean.setIdClient(0);
        viewBean.setName("");
        viewBean.setDocument("");
        viewBean.setTelephone("");
    }

}
