package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Classes.Pet;
import Classes.Shop;
import Database.DBHandler;
import Interfaces.Creator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller implements Creator {

    private List<CheckBox> checkAdd = new ArrayList<>();
    private List<CheckBox> checkDelete = new ArrayList<>();
    private List<Pet> petList = new ArrayList<>();
    private List<Pet> petSale = new ArrayList<>();
    private Pet pet = new Pet();
    private Shop zooShop = new Shop();

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
        zooShop = DBHandler.selectFromShop();
        /*
        if(zooShop.getID() == 0){
            zooShop.setID(1);
            zooShop.setShop("Barsik&Marsik");
            zooShop.setBalance(100000);
            DBHandler.insertIntoShop(zooShop);
        }
        */

        labelShop.setText("Магазин: " + zooShop.getShop());
        labelBalance.setText("Баланс: " + zooShop.getBalance() + " Рублей.");

        petList = DBHandler.selectFromPet();

        for(int i = 0; i < petList.size(); i++){
            checkAdd.add(checkBoxCreator(petList.get(i)));
            vboxAdd.getChildren().add(checkAdd.get(i));
        }

        buttonAdd.setOnAction(actionEvent -> {
            CheckBox checkBox = new CheckBox();
            Pet pet = new Pet(Integer.parseInt(fieldCost.getText()), Integer.parseInt(fieldID.getText()), fieldName.getText(), fieldBreed.getText(), false);
            if(checkPassport.isSelected()) {
                pet.setPassport(true);
            }

            DBHandler.insertInto(pet);
            petList.add(pet);
            if(pet.getPassport()) {
                checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + " Рублей" + "\nC паспортом\n" + "\n");
            }else {
                checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + " Рублей" + "\nБез паспорта\n" + "\n");
            }
            checkAdd.add(checkBox);
            vboxAdd.getChildren().add(checkBox);
        });

        buttonBuy.setOnAction(actionEvent -> {
            for(int i = 0; i < checkAdd.size(); i++){
                if(checkAdd.get(i).isSelected()){
                    if(zooShop.getBalance() - petList.get(i).getCost() < 0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText(null);
                        alert.setContentText("Недостаточно средств.");
                        alert.showAndWait();
                        i++;
                    }else if(zooShop.getBalance() - petList.get(i).getCost() >= 0){
                        DBHandler.deleteFromPets(petList.get(i));
                        vboxAdd.getChildren().remove(i);
                        vboxDelete.getChildren().add(checkAdd.get(i));
                        checkDelete.add(checkAdd.get(i));
                        zooShop.setBalance(zooShop.getBalance() - petList.get(i).getCost());
                        petSale.add(petList.get(i));
                        petList.remove(i);
                        checkAdd.remove(i);
                    }
                }
            }
            DBHandler.updateShopInfo(zooShop);
            labelBalance.setText("Баланс: " + zooShop.getBalance() + " Рублей.");
        });

        buttonSale.setOnAction(actionEvent -> {
            for(int i = 0; i < checkDelete.size(); i++){
                if(checkDelete.get(i).isSelected()){
                    vboxDelete.getChildren().remove(i);
                    zooShop.setBalance(zooShop.getBalance() + petSale.get(i).getCost() * 2);
                    checkDelete.remove(i);
                }
            }
            DBHandler.updateShopInfo(zooShop);
            labelBalance.setText("Баланс: " + zooShop.getBalance() + " Рублей.");
        });

    }

    @Override
    public CheckBox checkBoxCreator(Pet pet){
        CheckBox checkBox = new CheckBox();

        if(pet.getPassport()) {
            checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + " Рублей" + "\nС паспортом\n" + "\n");
        } else if (!pet.getPassport()) {
            checkBox.setText("Имя: " + pet.getName() + "\nПорода: " + pet.getBreed() + "\nID: " + pet.getId() + "\nЦена: " + pet.getCost() + " Рублей" + "\nБез паспорта\n" + "\n");
        }
        return checkBox;
    }

}