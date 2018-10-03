package com.estacionamento.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
//        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + "~/db/database", "laislodi", "123")) {
//            H2Helper h2Helper = new H2Helper();
//
//            h2Helper.createDbStructure(connection);
//            h2Helper.initilizeData(connection);
//            h2Helper.updateData(connection);
//            h2Helper.deleteData(connection);
//
//            List<ClientEntity> personList = h2Helper.selectAll(connection);
//
//            personList.forEach(System.out::println);
//
//            ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//            //ClientController clientController = context.getBean(ClientController.class);
//            App app = new App();
//            app.start(primaryStage);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

        //ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        //ClientController clientController = context.getBean(ClientController.class);
        App app = new App();
        app.start(primaryStage);
    }


}
