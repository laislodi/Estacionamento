package com.estacionamento.app.view;

import com.estacionamento.app.controller.ClientController;
import com.estacionamento.app.controller.VehicleController;
import com.estacionamento.app.view.domain.ClientViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientListView extends View {

    private final ClientController clientController;
    private final VehicleController vehicleController;
    private final VehicleView vehicleView;

    private ResourceBundle bundle = ResourceBundle.getBundle("myLabels");
    private Stage vehicleWindow;
    private final ClientView clientView;
    private final Stage clientWindow;

    public ClientListView(ClientController clientController, VehicleController vehicleController) {
        this.clientController = clientController;
        this.vehicleController = vehicleController;
        this.vehicleView = new VehicleView(clientController, vehicleController);
        this.clientView = new ClientView(clientController, vehicleView);
        this.clientWindow = new Stage();
    }

    public Scene buildClientRegisterScene() {
        vehicleWindow = new Stage();
        vehicleWindow.setTitle(bundle.getString("vehicleTitle"));
        vehicleWindow.setMinWidth(600);
        vehicleWindow.setMinHeight(500);
        vehicleWindow.initModality(Modality.APPLICATION_MODAL);
        vehicleWindow.setScene(vehicleView.buildScene());

        clientWindow.setTitle(bundle.getString("clientTitle"));
        clientWindow.setMinWidth(600);
        clientWindow.setMinHeight(500);
        clientWindow.initModality(Modality.APPLICATION_MODAL);
        clientWindow.setScene(clientView.buildClientScene());

        Label lbSearchClient = new Label(bundle.getString("searchClient"));
        ObservableList<String> stringObservableList =
                FXCollections.observableArrayList(
                        bundle.getString("plate"),
                        bundle.getString("name")
                );
        ComboBox<String> cbFilter = new ComboBox<>();
        cbFilter.setItems(stringObservableList);
        cbFilter.getSelectionModel().select(0);

        TextField txFilter = new TextField();

        TableView<ClientViewBean> tbClientViewBean = buildTable();
        ObservableList<ClientViewBean> allClients = findAllClients();
        tbClientViewBean.setItems(allClients);

        Button btSearchClient = new Button(bundle.getString("search"));
        btSearchClient.setOnAction(e -> {
            if (cbFilter.getSelectionModel().isSelected(0)) {
                tbClientViewBean.setItems(findClientsByPlate(txFilter.getText()));
            } else {
                tbClientViewBean.setItems(findClientsByName(txFilter.getText()));
            }
        });

        Button btNewVehicle = new Button(bundle.getString("btNewVehicle"));
        btNewVehicle.setOnAction(e -> {
            if (!tbClientViewBean.getSelectionModel().isEmpty()) {
                int selectedIndex = tbClientViewBean.getSelectionModel().getSelectedIndex();
                vehicleView.loadData(allClients.get(selectedIndex).getIdClient());
                vehicleWindow.showAndWait();
            }
        });

        Button btRegisterClient = new Button(bundle.getString("register"));
        btRegisterClient.setOnAction(e -> {
            clientWindow.showAndWait();
        });
        Button btEdit = new Button(bundle.getString("edit"));
        btEdit.setOnAction(e -> {
            ClientViewBean selectedClientViewBean = tbClientViewBean.getSelectionModel().getSelectedItem();
            clientView.loadData(selectedClientViewBean.getIdClient());
            clientWindow.showAndWait();

        });
        Button btClear = new Button(bundle.getString("clear"));
        btClear.setOnAction(e -> {

//            tbClientViewBean.getSelectionModel().clearSelection();
        });

        Button btDelete = new Button(bundle.getString("delete"));
        btDelete.setOnAction(e -> deleteClient(tbClientViewBean.getSelectionModel().getSelectedItem()));

        HBox lytFilter = new HBox(cbFilter, txFilter, btSearchClient);
        lytFilter.setPadding(new Insets(10,10,10,10));
        HBox lytButt = new HBox();
        lytButt.setPadding(new Insets(10,0,10,0));
        lytButt.getChildren().addAll(btNewVehicle, btRegisterClient, btEdit, btDelete, btClear);

        BorderPane lytButtons = new BorderPane();
        lytButtons.setRight(lytButt);
        VBox lytMain = new VBox(lbSearchClient, lytFilter, tbClientViewBean, lytButtons);

        return new Scene(lytMain,600,600);
    }

    private TableView<ClientViewBean> buildTable() {
        TableView<ClientViewBean> clientViewBeanTableView = new TableView<>();

        TableColumn<ClientViewBean, String> nameColumn = new TableColumn<>(bundle.getString("name"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ClientViewBean, String> documentColumn = new TableColumn<>(bundle.getString("document"));
        documentColumn.setMinWidth(150);
        documentColumn.setCellValueFactory(new PropertyValueFactory<>("document"));

        TableColumn<ClientViewBean, String> telephoneColumn = new TableColumn<>(bundle.getString("telephone"));
        telephoneColumn.setMinWidth(150);
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        clientViewBeanTableView.getColumns().addAll(nameColumn, documentColumn, telephoneColumn);

        return clientViewBeanTableView;
    }

    private ObservableList<ClientViewBean> findClientsByName(String name) {
        ObservableList<ClientViewBean> clientViewByName = FXCollections.observableArrayList();

        List<ClientViewBean> viewBeanList = clientController.findClientsByName(name);
        clientViewByName.addAll(viewBeanList);

        return clientViewByName;
    }

    private ObservableList<ClientViewBean> findClientsByPlate(String plate) {
        ObservableList<ClientViewBean> clientViewByPlate = FXCollections.observableArrayList();

        List<Long> idClientList = vehicleController.findIdClientsByPlate(plate);
        List<ClientViewBean> list = new ArrayList<>();
        for (Long id : idClientList ) {
            ClientViewBean viewBean = clientController.findClientById(id);
            list.add(viewBean);
        }

        clientViewByPlate.addAll(list);
        return clientViewByPlate;
    }

    private ObservableList<ClientViewBean> findAllClients() {
        ObservableList<ClientViewBean> allClients = FXCollections.observableArrayList();

        List<ClientViewBean> listOfAllClients = clientController.findAllClients();
        allClients.addAll(listOfAllClients);

        return allClients;
    }

    private void deleteClient(ClientViewBean viewBean) {
        if (ConfirmBox.display(
                bundle.getString("clientDeleteConfirmationTitle"),
                bundle.getString("clientDeleteConfirmationMessage")))
        clientController.deleteClient(viewBean.getIdClient());
    }
}
