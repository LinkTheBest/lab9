package com.gnida.izkadetov.RegistrationView;

import com.gnida.izkadetov.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistrationViewController {

    private Socket socket;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;
    private MessageToClient message;
    private Command command;
    private Alert alert;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button registrationButton;

    @FXML
    private void initialize() {

        registrationButton.setOnAction(event -> {
            try {
                socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
                toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
                fromServerMessageHandler = new FromServerMessageHandler(socket);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connection error");
                alert.setContentText("Server is unavialible!");
                alert.showAndWait();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/connection.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) registrationButton.getScene().getWindow();
                    stage.setTitle("Connection");
                    stage.setScene(scene);
                } catch (Exception ex) {
                    System.out.println(e.getMessage());
                }
            }
            sendToServer();
        });
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendToServer() {
        try {
            command = new Command(ListOfCommands.REGISTRATION, loginTextField.getText(), passwordField.getText());
            toServerMessageHandler.sendMessage(command);
            readFromServer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFromServer() {
        cachedThreadPool.execute(() -> {
            try {
                message = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (message.getMessage().equals("Такой логин уже существует! Попробуйте другой") | message.getMessage().equals("Ошибка регистрации")) {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning!");
                        alert.setContentText("Такой логин уже существует! Попробуйте другой");
                        alert.showAndWait();
                    } else {
                        System.out.println("Успех");
                    }
                });

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
