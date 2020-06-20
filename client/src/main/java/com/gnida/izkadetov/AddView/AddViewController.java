package com.gnida.izkadetov.AddView;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.MainView.MainViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddViewController {

    private String login;
    private String password;
    private Socket socket;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;
    private MessageToClient messageToClient;
    private ExecutorService cachedThread = Executors.newCachedThreadPool();


    @FXML
    private TextField idTextFIeld;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField xTextField;

    @FXML
    private TextField yTextField;

    @FXML
    private TextField healthTextField;

    @FXML
    private TextField chapterTextField;

    @FXML
    private TextField weaponTextField;

    @FXML
    private TextField meleeTextField;

    @FXML
    private TextField categoryTextField;


    @FXML
    private Button addButton;

    @FXML
    private Button returnButton;

    @FXML
    private void initialize() {

        addButton.setOnAction(event -> {
            addButtonAction();
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        });

        returnButton.setOnAction(event -> {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
        });

    }

    public void setLoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void addButtonAction() {
        SpaceMarine spaceMarine = new SpaceMarine();
        try {
            if (!nameTextField.getText().equals(null))
                spaceMarine.setName(nameTextField.getText());
            if (!idTextFIeld.getText().equals(null))
                spaceMarine.setId(Integer.valueOf(idTextFIeld.getText()));
            if (!xTextField.getText().equals(null))
                spaceMarine.setXCoordinate(Double.valueOf(xTextField.getText()));
            if (!yTextField.getText().equals(null))
                spaceMarine.setYCoordinate(Float.valueOf(xTextField.getText()));
            if (!healthTextField.getText().equals(null))
                spaceMarine.setHealth(Integer.valueOf(healthTextField.getText()));
            if (!chapterTextField.getText().equals(null))
                spaceMarine.setChapter(chapterTextField.getText());
            if (!weaponTextField.getText().equals(null))
                spaceMarine.setWeaponType(weaponTextField.getText());
            if (!meleeTextField.getText().equals(null))
                spaceMarine.setMeleeWeapon(meleeTextField.getText());
            if (!categoryTextField.getText().equals(null))
                spaceMarine.setCategory(categoryTextField.getText());
        } catch (Exception e) {
            showErrorAlert();
        }
        try {

            socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
            toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
            Command command = new Command(ListOfCommands.ADD, spaceMarine, login, password);
            toServerMessageHandler.sendMessage(command);
            recieveMessage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void recieveMessage() {
        cachedThread.execute(() -> {
            try {
                fromServerMessageHandler = new FromServerMessageHandler(socket);
                messageToClient = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (messageToClient.getMessage().startsWith("Элемент успешно добавлен! ")) {
                        showPositiveAlert();
                    }
                });
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.getMessage());
            }

        });

    }

    public Alert showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty fields");
        alert.setContentText("Error with filling fields!");
        alert.showAndWait();
        return alert;
    }

    public Alert showPositiveAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Succeed!");
        alert.setContentText("Element added!");
        alert.showAndWait();
        return alert;
    }

}
