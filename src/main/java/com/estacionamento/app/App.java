package com.estacionamento.app;

import com.estacionamento.app.controller.ClientController;
import com.estacionamento.app.controller.VehicleController;
import com.estacionamento.app.persistency.ClientDao;
import com.estacionamento.app.persistency.VehicleDao;
import com.estacionamento.app.service.ClientService;
import com.estacionamento.app.service.VehicleService;
import com.estacionamento.app.view.ClientListView;
import com.estacionamento.app.view.ClientView;
import com.estacionamento.app.view.ConfirmBox;
import com.estacionamento.app.view.EntriesControlView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class App {
    private ResourceBundle myBundle = ResourceBundle.getBundle("MyLabels");
    private Stage window;
    private Scene mainScene;
    private Scene clientScene;

    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        clientScene = buildClientScene();

        window.setScene(clientScene);
        window.setTitle(myBundle.getString("clientTitle"));
        window.show();

//        mainScene = buildMainScene(myBundle);
//        window.setScene(mainScene);
//        window.setTitle(myBundle.getString("AppTitle"));
//        window.show();
    }

    private Scene buildClientScene() {
        ClientDao dao = new ClientDao();
        ClientService service = new ClientService(dao);
        VehicleDao vehicleDao = new VehicleDao();
        VehicleService vehicleService = new VehicleService(vehicleDao);
        VehicleController vehicleController = new VehicleController(vehicleService);
        ClientController controller = new ClientController(service,vehicleService);
        ClientListView clientListView = new ClientListView(controller,vehicleController);
        ClientView clientView = new ClientView(controller, vehicleView);

        //return clientView.buildClientScene();
        return clientListView.buildClientRegisterScene();
    }

    private Scene buildMainScene(ResourceBundle bundle) {
        EntriesControlView entriesControlView = new EntriesControlView();

        return entriesControlView.buildMainScene(bundle);
    }

    private void closeProgram() {
        boolean confirmation = ConfirmBox.display(
                myBundle.getString("closeConfirmTitle"),
                myBundle.getString("closeConfirmMessage")
        );

        if (confirmation) {
            window.close();
        }
    }
}
