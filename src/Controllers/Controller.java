package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Classes.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {
    private Pet pet;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelBalance;

    @FXML
    private Label labelShop;

    @FXML
    private Button buttonSale;

    @FXML
    private Button buttonBuy;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabeDelete;

    @FXML
    private AnchorPane paneDelete;

    @FXML
    private VBox vboxDelete;

    @FXML
    private Tab tabAdd;

    @FXML
    private AnchorPane paneAdd;

    @FXML
    private VBox vboxAdd;

    @FXML
    void initialize() {


    }
}