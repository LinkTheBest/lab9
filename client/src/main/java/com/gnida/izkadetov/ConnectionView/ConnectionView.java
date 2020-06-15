package com.gnida.izkadetov.ConnectionView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnectionView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/connection.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Connection");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
