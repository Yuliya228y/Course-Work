package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Classes.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {
    private Pet pet;
    List<CheckBox> checkList = new ArrayList<>();

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
    private Button buttonAdd;

    @FXML
    private TextField fieldCost;

    @FXML
    private TextField fieldBreed;

    @FXML
    private TextField fieldID;

    @FXML
    private TextField fieldName;

    @FXML
    private CheckBox checkPassport;

    @FXML
    void initialize() {
        labelShop.setText("Магазин: Барсик&Марсик");
        buttonAdd.setOnAction(actionEvent -> {
            Pet pet = new Pet(Integer.parseInt(fieldCost.getText()), Integer.parseInt(fieldID.getText()), fieldName.getText(), fieldBreed.getText(), false);
            if(checkPassport.isSelected()) pet.setPassport(true);
            Pet.add(pet);
            CheckBox checkBox = new CheckBox();
            if(pet.getPassport() == true) {
                checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + "\nС пасспортом\n" + "\n");
            } else if (pet.getPassport() == false) {
                checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + "\nБез пасспорта\n" + "\n");
            }

            vboxAdd.getChildren().add(checkBox);

        });
    }
}