package com.gnida.izkadetov.MainView;

import com.gnida.izkadetov.DataBaseManager;
import com.gnida.izkadetov.LoginView.LoginViewController;
import com.gnida.izkadetov.SpaceMarine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainViewController {

    private Socket socket;
    private DataBaseManager dataBaseManager;
    private ObservableList<SpaceMarine> observableList = FXCollections.observableArrayList();

    @FXML
    private Label userLoginLabel;

    @FXML
    private Pane userInfoPane;

    @FXML
    private Pane commandsButtonPane;

    @FXML
    private Button logoutButton;

    @FXML
    private Button addButton;

    @FXML
    private Button addMaxButton;

    @FXML
    private Button addMinButton;

    @FXML
    private Button removeLowerButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button executeButton;

    @FXML
    private Button sumButtton;

    @FXML
    private Button printDesButton;

    @FXML
    private Button printDeshealthButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button updateButton;

    @FXML
    private Pane tableViewPane;


    @FXML
    private void initialize() {

        try {
            tableViewPane.getChildren().addAll(initTableView());
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

        exitButton.setOnAction(event -> {
            System.exit(0);
        });

        logoutButton.setOnAction(event -> {
            try {
                logOutButtonAction();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });


    }

    public void setUserLogin(String userLogin) {
        userLoginLabel.setText(userLogin);
    }

    public void logOutButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        LoginViewController loginViewController = loader.getController();
        loginViewController.setSocket(socket);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setTitle("Login Page");
        stage.setScene(new Scene(root, 600, 400));
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public TableView<SpaceMarine> initTableView() {
        TableView<SpaceMarine> objectsTableView = new TableView<>();
        objectsTableView.prefHeightProperty().bind(tableViewPane.heightProperty());
        objectsTableView.prefWidthProperty().bind(tableViewPane.widthProperty());
        TableColumn<SpaceMarine, Integer> spcIdCol = new TableColumn<>("spcid");
        TableColumn<SpaceMarine, String> spcNameCol = new TableColumn<>("spcname");
        TableColumn<SpaceMarine, Double> xCol = new TableColumn<>("x");
        TableColumn<SpaceMarine, Double> yCol = new TableColumn<>("y");
        TableColumn<SpaceMarine, Double> healthCol = new TableColumn<>("health");
        TableColumn<SpaceMarine, Double> categoryCol = new TableColumn<>("category");
        TableColumn<SpaceMarine, Double> weaponTypeCol = new TableColumn<>("weapontype");
        TableColumn<SpaceMarine, Double> meleeWeaponTypeCol = new TableColumn<>("meleeweapon");
        TableColumn<SpaceMarine, Double> chapterCol = new TableColumn<>("chapter");

//        spcIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//        spcNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));
//        yCol.setCellValueFactory(new PropertyValueFactory<>("y"));
//        healthCol.setCellValueFactory(new PropertyValueFactory<>("health"));
//        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
//        weaponTypeCol.setCellValueFactory(new PropertyValueFactory<>("weaponType"));
//        meleeWeaponTypeCol.setCellValueFactory(new PropertyValueFactory<>("meleeWeapon"));
//        chapterCol.setCellValueFactory(new PropertyValueFactory<>("chapter"));

        objectsTableView.getColumns().addAll(spcIdCol, spcNameCol, xCol, yCol, healthCol, categoryCol, weaponTypeCol, meleeWeaponTypeCol, chapterCol);
//        try {
//            //ObservableList<SpaceMarine> list = getAllElementsFromDataBase();
//            //objectsTableView.setItems(list);
//            objectsTableView.getColumns().addAll(spcIdCol, spcNameCol, xCol, yCol, healthCol, categoryCol, weaponTypeCol, meleeWeaponTypeCol, chapterCol);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getSQLState());
//        }
        return objectsTableView;
    }

    public ObservableList<SpaceMarine> getAllElementsFromDataBase() {
        dataBaseManager = new DataBaseManager();
        try {
            PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("select * from spacemarines");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SpaceMarine spc = new SpaceMarine();
                spc.setHealth(resultSet.getInt("health"));
                spc.setMeleeWeapon(resultSet.getString("meleeweapon"));
                spc.setYCoordinate(resultSet.getFloat("y"));
                spc.setXCoordinate(resultSet.getDouble("x"));
                spc.setName(resultSet.getString("spcname"));
                spc.setId(resultSet.getInt("scpid"));
                spc.setCategory(resultSet.getString("category"));
                spc.setWeaponType(resultSet.getString("weapontype"));
                spc.setChapter(resultSet.getString("chapter"));
                observableList.add(spc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return observableList;
    }
}
