package com.gnida.izkadetov.MainView;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.AddView.AddViewController;
import com.gnida.izkadetov.LoginView.LoginViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewController {

    private Command commandShow;
    private Socket socket;
    private String login;
    private String pwd;
    private ObservableList<SpaceMarine> observableList = FXCollections.observableArrayList();
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;
    private Executor cachedThread = Executors.newCachedThreadPool();
    private MessageToClient messageToClient;

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
    private Button removeButton;
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
    private Label userIdLabel;

    private TableView<SpaceMarine> tableView;

    @FXML
    private void initialize() {

        tableViewPane.getChildren().addAll(initTableView());

        updateButton.setOnAction(event -> {
            try {
                socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
                toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
                fromServerMessageHandler = new FromServerMessageHandler(socket);
                sendRequestForGettingElements();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        exitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        logoutButton.setOnAction(event -> {
            try {
                logOutButtonAction();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        addButton.setOnAction(event -> {
            try {
                moveToAddElementScreen("");
                updateButton.fire();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        addMaxButton.setOnAction(event -> {
            try {
                moveToAddElementScreen("max");
                updateButton.fire();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        addMinButton.setOnAction(event -> {
            try {
                moveToAddElementScreen("min");
                updateButton.fire();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        removeButton.setOnAction(event -> {
            SpaceMarine spaceMarine = tableView.getSelectionModel().getSelectedItem();
            if (spaceMarine.getUserId() == Integer.valueOf(userIdLabel.getText())) {
                try {
                    socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
                    toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
                    fromServerMessageHandler = new FromServerMessageHandler(socket);
                } catch (IOException e) {
                }
                sendRemoveRequest(spaceMarine.getId());
                tableView.getItems().remove(spaceMarine);
            }

        });

        clearButton.setOnAction(event -> {
            try {
                socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
                toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
                fromServerMessageHandler = new FromServerMessageHandler(socket);
            } catch (IOException e) {
            }
            sendClearRequest();
            updateButton.fire();
        });

        sumButtton.setOnAction(event -> {
            try {
                sendSumRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        infoButton.setOnAction(event -> {
            try {
                sendInfoRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public Alert sendingError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText("Ошибка отправки!");
        alert.showAndWait();
        return alert;
    }

    public void setUserLogin(String userLogin, String pwd, int userID) {
        userLoginLabel.setText(userLogin);
        userIdLabel.setText(String.valueOf(userID));
        commandShow = new Command(ListOfCommands.SHOW, userLoginLabel.getText(), pwd);
        login = userLogin;
        this.pwd = pwd;
    }

    public void setSocket(Socket loginSocket) {
        try {
            socket = new Socket(loginSocket.getInetAddress().getHostName(), loginSocket.getPort());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public TableView<SpaceMarine> initTableView() {
        TableView<SpaceMarine> objectsTableView = new TableView<>();
        objectsTableView.prefHeightProperty().bind(tableViewPane.heightProperty());
        objectsTableView.prefWidthProperty().bind(tableViewPane.widthProperty());
        TableColumn<SpaceMarine, Integer> spcIdCol = new TableColumn<>("spcid");
        TableColumn<SpaceMarine, String> spcNameCol = new TableColumn<>("spcname");
        TableColumn<SpaceMarine, Double> xCol = new TableColumn<>("x");
        TableColumn<SpaceMarine, Float> yCol = new TableColumn<>("y");
        TableColumn<SpaceMarine, Integer> healthCol = new TableColumn<>("health");
        TableColumn<SpaceMarine, String> categoryCol = new TableColumn<>("category");
        TableColumn<SpaceMarine, String> weaponTypeCol = new TableColumn<>("weapontype");
        TableColumn<SpaceMarine, String> meleeWeaponTypeCol = new TableColumn<>("meleeweapon");
        TableColumn<SpaceMarine, String> chapterCol = new TableColumn<>("chapter");
        TableColumn<SpaceMarine, Integer> userIdCol = new TableColumn<>("userID");

        spcIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        spcNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));
        yCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        healthCol.setCellValueFactory(new PropertyValueFactory<>("health"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        weaponTypeCol.setCellValueFactory(new PropertyValueFactory<>("weaponType"));
        meleeWeaponTypeCol.setCellValueFactory(new PropertyValueFactory<>("meleeWeapon"));
        chapterCol.setCellValueFactory(new PropertyValueFactory<>("chapterName"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        objectsTableView.getColumns().addAll(spcIdCol, spcNameCol, xCol, yCol, healthCol, categoryCol, weaponTypeCol, meleeWeaponTypeCol, chapterCol, userIdCol);
        return objectsTableView;
    }

    public void sendRequestForGettingElements() {
        try {
            toServerMessageHandler.sendMessage(commandShow);
            getServerMessage();
        } catch (Exception e) {
            sendingError();
        }
    }

    public void getServerMessage() {
        cachedThread.execute(() -> {
            try {
                messageToClient = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (messageToClient.getSpaceMarines().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Info");
                        alert.setContentText("Пустая коллекция!");
                        alert.showAndWait();
                    }
                });
                observableList.removeAll();
                observableList.clear();
                observableList = setElementsForTableView(new ArrayList<>(messageToClient.getSpaceMarines()));
                tableView = initTableView();
                tableView.getItems().clear();
                tableView.setItems(observableList);
                Platform.runLater(() -> {
                    tableViewPane.getChildren().addAll(tableView);
                });
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public ObservableList<SpaceMarine> setElementsForTableView(ArrayList<SpaceMarine> spaceMarines) {
        Iterator<SpaceMarine> iterator = spaceMarines.iterator();
        while (iterator.hasNext()) {
            observableList.add(iterator.next());
        }
        return observableList;
    }

    public void moveToAddElementScreen(String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/add.fxml"));
        Parent root = loader.load();
        AddViewController controller = loader.getController();
        controller.setLoginAndPassword(login, pwd);
        controller.setAddStatus(status);
        controller.setSocket(socket);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void sendRemoveRequest(int id) {
        try {
            toServerMessageHandler.sendMessage(new Command(ListOfCommands.REMOVE_BY_ID, id, login, pwd));
            getRemoveAnswer();
        } catch (Exception e) {
            sendingError();
        }
    }

    public void getRemoveAnswer() {
        cachedThread.execute(() -> {
            try {
                messageToClient = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (messageToClient.getMessage().startsWith("Элемент коллекции успешно удалён.")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succeed!");
                        alert.setContentText("Element removed!");
                        alert.showAndWait();
                    }
                });

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void sendClearRequest() {
        try {
            toServerMessageHandler.sendMessage(new Command(ListOfCommands.CLEAR, login, pwd));
            getClearAnswer();
        } catch (Exception e) {
            sendingError();
        }
    }

    public void getClearAnswer() {
        cachedThread.execute(() -> {
            try {
                messageToClient = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    if (messageToClient.getMessage().startsWith("Коллекция была очищена")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succeed!");
                        alert.setContentText("Collection Cleared!");
                        alert.showAndWait();
                    }
                });

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void sendSumRequest() throws IOException {
        socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
        toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
        toServerMessageHandler.sendMessage(new Command(ListOfCommands.SUM_OF_HEALTH, login, pwd));
        getSumAnswer();
    }

    public void getSumAnswer() {
        cachedThread.execute(() -> {
            fromServerMessageHandler = new FromServerMessageHandler(socket);
            try {
                MessageToClient message = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sum");
                    alert.setContentText(message.getMessage());
                    alert.showAndWait();
                });
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendInfoRequest() throws IOException {
        socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
        toServerMessageHandler = new ToServerMessageHandler(socket, socket.getPort());
        toServerMessageHandler.sendMessage(new Command(ListOfCommands.INFO, login, pwd));
        getInfoAnswer();
    }

    public void getInfoAnswer() {
        cachedThread.execute(() -> {
            fromServerMessageHandler = new FromServerMessageHandler(socket);
            try {
                MessageToClient message = fromServerMessageHandler.getMessage();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setContentText(message.getMessage());
                    alert.showAndWait();
                });
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
