package com.gnida.izkadetov.LoginView;

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

public class LoginViewController {

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
    private Button loginButton;

    @FXML
    private Button registrationButton;

    @FXML
    private void initialize() {

        loginButton.setOnAction(event -> {
            setLoginButtonAction();
        });

        registrationButton.setOnAction(event -> {
            try {
                setRegistrationButtonAction();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
        fromServerMessageHandler = new FromServerMessageHandler(socket);
    }

    public void setLoginButtonAction() {
        try {
            System.out.println(socket.isConnected());
            command = new Command(ListOfCommands.LOGIN, loginTextField.getText(), passwordField.getText());
            toServerMessageHandler.sendMessage(command);
            RecieveMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void RecieveMessage() {
        cachedThreadPool.execute(() -> {
            try {
                message = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (message.getMessage().equals("Такой пользователь не существует! Зарегистрируйтесь !") | message.getMessage().equals("Произошла ошибка") | message.getMessage().equals("Ошибка входа")) {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning!");
                        alert.setContentText("Такой пользователь не существует! Зарегистрируйтесь !");
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

    public void setRegistrationButtonAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/registration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) registrationButton.getScene().getWindow();
        stage.setTitle("Registration");
        stage.setScene(scene);

    }
}
