package com.gnida.izkadetov.ChangeView;


import com.gnida.izkadetov.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChangeViewController {

    private Command command;
    private SpaceMarine spaceMarine;
    private String login;
    private String password;
    private Socket socket;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;
    private MessageToClient messageToClient;
    private ExecutorService cachedThread = Executors.newCachedThreadPool();


    @FXML
    private Label idLabel;

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
    private Button updateButton;

    @FXML
    private Button returnButton;

    @FXML
    private void initialize() {


        updateButton.setOnAction(event -> {
            updateButtonAction();
            Stage stage = (Stage) updateButton.getScene().getWindow();
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

    public void updateButtonAction() {
        SpaceMarine newSpaceMarine = new SpaceMarine();

        try {
            if (!nameTextField.getText().equals(null))
                newSpaceMarine.setName(nameTextField.getText());
            newSpaceMarine.setId(spaceMarine.getId());
            if (!xTextField.getText().equals(null))
                newSpaceMarine.setXCoordinate(Double.valueOf(xTextField.getText()));
            if (!yTextField.getText().equals(null))
                newSpaceMarine.setYCoordinate(Float.valueOf(yTextField.getText()));
            if (!healthTextField.getText().equals(null))
                newSpaceMarine.setHealth(Integer.valueOf(healthTextField.getText()));
            if (!chapterTextField.getText().equals(null))
                newSpaceMarine.setChapter(chapterTextField.getText());
            if (!weaponTextField.getText().equals(null))
                newSpaceMarine.setWeaponType(weaponTextField.getText());
            if (!meleeTextField.getText().equals(null))
                newSpaceMarine.setMeleeWeapon(meleeTextField.getText());
            if (!categoryTextField.getText().equals(null))
                newSpaceMarine.setCategory(categoryTextField.getText());
        } catch (Exception e) {
            showErrorAlert();
        }
        try {
            if (newSpaceMarine.toString().equals(spaceMarine.toString())) {
                showSameAlert();
            } else {
                socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
                toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
                command = new Command(ListOfCommands.UPDATE, newSpaceMarine, login, password);
                toServerMessageHandler.sendMessage(command);
                recieveMessage();
            }
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
                    if (messageToClient.getMessage().startsWith("Элемент успешно добавлен! ") | messageToClient.getMessage().startsWith("Команда была выполнена")) {
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succeed!");
        alert.setContentText("Element Updated!");
        alert.showAndWait();
        return alert;
    }

    public Alert showSameAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Nothing changed");
        alert.showAndWait();
        return alert;
    }


    public void setSpaceMarine(SpaceMarine spaceMarine) {
        idLabel.setText(String.valueOf(spaceMarine.getId()));
        this.spaceMarine = spaceMarine;
    }

}