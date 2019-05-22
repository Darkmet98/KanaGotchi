package UI.pkgControllers;

import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgExceptions.ItemNotSelected;
import Engine.pkgItems.Items;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Shop_Controller extends Common_Controller{

    //Values
    @FXML
    TableView ItemTable;
    @FXML
    TableColumn<String, Items> Name;
    @FXML
    TableColumn<String, Items> Price;
    @FXML
    TableColumn<String, Items> Health;
    @FXML
    TableColumn<String, Items> Experience;
    @FXML
    Label Money;



    /*
     * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Only initialize from the first time
        if(Money.getText().equals("VALUE")) {
            //Read the table
            SetTable();
            //Set the money listener
            LoadMoneyListener();
            //Load the items
            LoadItemTable();
        }
    }

    /*
    * Load the item table
     */
    private void LoadItemTable() {
        for(int i = 0; i < Ingame_Controller.game.getItem().getItemList().size(); i++) {
            Items item = (Items) Ingame_Controller.game.getItem().getItemList().get(i);
            ItemTable.getItems().add(item);
        }
    }

    /*
    * Set the item shop table
     */
    private void SetTable() {
            Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            Health.setCellValueFactory(new PropertyValueFactory<>("HealthRecovered"));
            Experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
    }

    /*
    * Load the money listener
     */
    private void LoadMoneyListener() {
        Money.setText(String.valueOf(Ingame_Controller.game.getMoney()));
        //Money Listener
        Ingame_Controller.game.getMoneyProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> Money.setText(String.valueOf(Ingame_Controller.game.getMoney()))));
    }

    /*
    * Buy a item
     */
    @FXML
    public void Buy() {
        try{
            Ingame_Controller.game.Buy(ItemTable.getSelectionModel().getSelectedIndex());
        } catch (InsufficientMoney | ItemNotSelected  msg) {
          ShowInfoMsg(msg.getMessage());
        }
    }
}
