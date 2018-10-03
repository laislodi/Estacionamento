package com.estacionamento.app.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class View {

    public BorderPane buildTitleLayout(String title) {
        Label lbSceneTitle = new Label(title);
        lbSceneTitle.setMinSize(200d,20d);
        BorderPane lytTitle = new BorderPane();
        ResourceBundle myBundle = ResourceBundle.getBundle("MyLabels");

        Label lbSpotNumber = new Label(myBundle.getString("lbSpotNumber"));
        Label lbRealTime = new Label(String.format(LocalDateTime.now().toString()," %td-%te-%ty %tH:%tM"));
        VBox lytTitleDetails = new VBox();
        lytTitleDetails.getChildren().addAll(lbSpotNumber,lbRealTime);

        lytTitle.setLeft(lbSceneTitle);
        lytTitle.setRight(lytTitleDetails);

        return lytTitle;
    }

//    default HBox buildBottomLine(ResourceBundle myBundle) {
//        // Third line, the bottom one - It contais the clear and exit buttons
//        HBox lytBottomButtons = new HBox();
//        Button btClear = new Button(myBundle.getString("clear"));
//        Button btExit = new Button(myBundle.getString("exit"));
//        lytBottomButtons.getChildren().addAll(btClear,btExit);
//
//        return lytBottomButtons;
//    }

}
