package com.gnida.izkadetov.ConnectionView;

import com.gnida.izkadetov.ConnectionChecker;
import com.gnida.izkadetov.LoginView.LoginViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.Socket;

public class ConnectionController {

    private ConnectionChecker connectionChecker;

    @FXML
    private TextField hostTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button connectButton;

    @FXML
    private void initialize() {

        connectButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            try {
                int port = Integer.valueOf(portTextField.getText());
//                connectionChecker = new ConnectionChecker(hostTextField.getText(), port);
//                connectionChecker.socketConnector();
                Socket socket = new Socket(hostTextField.getText(), port);
                if (socket.isConnected()) {
                    System.out.println(socket);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                    Parent root = loader.load();
                    LoginViewController loginViewController = loader.getController();
                    loginViewController.setSocket(socket);
                    Stage stage = (Stage) connectButton.getScene().getWindow();
                    stage.setTitle("Login Page");
                    stage.setScene(new Scene(root));
                } else {
                    alert.setTitle("Error");
                    alert.setContentText("Cannot connect to Server. Try again!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                alert.setTitle("Error");
                alert.setContentText("Incorrect host/port!");
                alert.showAndWait();
            }
        });
    }
}
