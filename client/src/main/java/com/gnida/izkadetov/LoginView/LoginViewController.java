package com.gnida.izkadetov.LoginView;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.MainView.MainViewController;
import com.gnida.izkadetov.RegistrationView.RegistrationViewController;
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
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setTitle("Connection");
                    stage.setScene(scene);
                } catch (Exception ex) {
                    System.out.println(e.getMessage());
                }
            }
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
    }

    public void setLoginButtonAction() {
        try {
            command = new Command(ListOfCommands.LOGIN, loginTextField.getText(), passwordField.getText());
            toServerMessageHandler.sendMessage(command);
            recieveMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void recieveMessage() {
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
                        try {
                            goToMainView();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println(e.getCause());
                        }
                    }
                });

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

    }

    public void setRegistrationButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/registration.fxml"));
        Parent root = loader.load();
        RegistrationViewController registrationViewController = loader.getController();
        registrationViewController.setSocket(socket);
        Scene scene = new Scene(root);
        Stage stage = (Stage) registrationButton.getScene().getWindow();
        stage.setTitle("Registration");
        stage.setScene(scene);

    }

    public void goToMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        MainViewController mainViewController = loader.getController();
        mainViewController.setUserLogin(loginTextField.getText());
        mainViewController.setSocket(socket);
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Working area");
        stage.setScene(scene);
    }
}
