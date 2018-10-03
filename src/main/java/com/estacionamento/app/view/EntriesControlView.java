package com.estacionamento.app.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class EntriesControlView extends View {

    public Scene buildMainScene(ResourceBundle bundle) {
        Scene mainScene;

        // main layout - It contains everything
        BorderPane lytMain = new BorderPane();
        lytMain.setPadding(new Insets(10,10,10,10)); // borders

        // First Line - It contais the title in a horizon Box that contais a label with the title and a vertical Box with the number of parking spots and the real time
        BorderPane lytTitle = buildTitleLayout(bundle.getString("appTitle"));
        // Second line - It contains all of the scene, except the bottom buttons, in two columns
        HBox lytCenter = new HBox();
        // Entry Side
        VBox lytEntrySide = buildEntrySide(bundle);
        // Exit Side
        VBox lytExitSide = buildExitSide(bundle);
        // End of Second Line
        lytCenter.getChildren().addAll(lytEntrySide,lytExitSide);
        // Third Line - It contais the clear and exit buttons
        HBox lytBottomButtons = buildBottomLine(bundle);

        // Setting the Main Layout
        lytMain.setTop(lytTitle);
        lytMain.setLeft(lytEntrySide);
        lytMain.setRight(lytExitSide);
        lytMain.setBottom(lytBottomButtons);

        mainScene = new Scene(lytMain,1000,700);

        return mainScene;
    }

    private VBox buildEntrySide(ResourceBundle myBundle) {
        VBox lytEntrySide = new VBox();
        Label lbEntryTitle = new Label(myBundle.getString("lbEntryTitle"));
        Label lbEntryInform = new Label(myBundle.getString("lbEntryInform"));

        HBox lytEntryFilter = new HBox();
        ObservableList<String> stringObservableList =
                FXCollections.observableArrayList(
                        myBundle.getString("plate"),
                        myBundle.getString("document")
                );
        ComboBox cbEntryFilter = new ComboBox(stringObservableList);
        cbEntryFilter.getSelectionModel().select(0);
        TextField txEntryFilter = new TextField();
        Button btEntrySearch = new Button(myBundle.getString("search"));
        lytEntryFilter.getChildren().addAll(cbEntryFilter,txEntryFilter,btEntrySearch);

        lytEntrySide.getChildren().addAll(lbEntryTitle,lbEntryInform,lytEntryFilter);
        return lytEntrySide;
    }

    private VBox buildExitSide(ResourceBundle myBundle) {
        VBox lytExitSide = new VBox();
        Label lbExitTitle = new Label(myBundle.getString("lbExitTitle"));
        Label lbExitInform = new Label(myBundle.getString("lbExitInform"));

        HBox lytExitFilter = new HBox();
        ObservableList<String> stringObservableList =
                FXCollections.observableArrayList(
                        myBundle.getString("barcode"),
                        myBundle.getString("plate")
                );
        ComboBox cbExitFilter = new ComboBox(stringObservableList);
        cbExitFilter.getSelectionModel().select(0);
        TextField txExitFilter = new TextField();
        Button btExitSearch = new Button(myBundle.getString("search"));
        lytExitFilter.getChildren().addAll(cbExitFilter,txExitFilter,btExitSearch);

        Button btRegisterExit = new Button(myBundle.getString("registerExit"));
        HBox lytValue = new HBox();
        String paymentValue = myBundle.getString("value").concat(" ").concat(myBundle.getString("currency"));
        Label lbValue = new Label(paymentValue);
        Label lbPaymentValue = new Label();
        Button btRegisterPayment = new Button(myBundle.getString("registerEntry"));
        lytValue.getChildren().addAll(lbValue,lbPaymentValue);

        lytExitSide.getChildren().addAll(lbExitTitle,lbExitInform,lytExitFilter,btRegisterExit,lytValue,btRegisterPayment);
        return lytExitSide;
    }

    private HBox buildBottomLine (ResourceBundle bundle) {
        HBox clearExitButtons = new HBox();
        Button btClear = new Button(bundle.getString("clear"));
        Button btExit = new Button(bundle.getString("exit"));
        clearExitButtons.getChildren().addAll(btClear,btExit);

        return clearExitButtons;
    }

}
