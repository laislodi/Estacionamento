package com.estacionamento.app.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class InfoBox {

    public static void display(String title, String message) {
        ResourceBundle myBundle = ResourceBundle.getBundle("MyLabels");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label lbMessage = new Label(message);
        Button btok = new Button(myBundle.getString("ok"));
        btok.setOnAction(e->window.close());

        VBox lytMain = new VBox();
        HBox lytButtons = new HBox();
        lytButtons.getChildren().addAll(btok);
        lytMain.getChildren().addAll(lbMessage,lytButtons);
        lytMain.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(lytMain);
        window.setScene(scene);
        window.showAndWait();

    }
}
