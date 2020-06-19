package com.gnida.izkadetov.AddView;

import com.gnida.izkadetov.SpaceMarine;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.Socket;

public class AddViewController {

    private String login;
    private String password;
    private Socket socket;

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
                spaceMarine.setId(Integer.valueOf(healthTextField.getText()));
            if (!chapterTextField.getText().equals(null))
                spaceMarine.setChapter(chapterTextField.getText());
            if (!weaponTextField.getText().equals(null))
                spaceMarine.setWeaponType(weaponTextField.getText());
            if (!meleeTextField.getText().equals(null))
                spaceMarine.setMeleeWeapon(meleeTextField.getText());
            if (!categoryTextField.getText().equals(null))
                spaceMarine.setCategory(categoryTextField.getText());
        } catch (Exception e) {
            showAlert();
        }

        System.out.println(spaceMarine.toString());


    }

    public Alert showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty fields");
        alert.setContentText("Error with filling fields!");
        alert.showAndWait();
        return alert;
    }

}
