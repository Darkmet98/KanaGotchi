package UI.pkgControllers;

import Engine.pkgExceptions.ItemIsZero;
import Engine.pkgExceptions.ItemNotSelected;
import Engine.pkgItems.Items;
import Engine.pkgItems.ItemsObtained;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Food_Controller extends Common_Controller {

    //Values
    @FXML
    TableView ItemTable;
    @FXML
    TableColumn<String, ItemsObtained> Health;
    @FXML
    TableColumn<String, ItemsObtained> Experience;
    @FXML
    TableColumn<String, ItemsObtained> Name;
    @FXML
    TableColumn<String, ItemsObtained> Amount;

    /*
     * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Set the table
        SetTable();
        //Load the item table
        LoadItemTable();
        //Load the game valus
        LoadValues();
    }

    /*
    * Set the table cells
     */
    private void SetTable() {
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Health.setCellValueFactory(new PropertyValueFactory<>("Health"));
        Experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
    }

    /*
    * Load the table values
     */
    private void LoadItemTable() {
        for(int i = 0; i < Ingame_Controller.game.getItem().getItemList().size(); i++) {
            Items item = (Items) Ingame_Controller.game.getItem().getItemList().get(i);
            ItemTable.getItems().add(new ItemsObtained(game.getItemsOwned().get(i), item.getName(), item.getHealthRecovered(), item.getExperience()));
        }
    }

    /*
    * Eat a item
     */
    @FXML
    private void Eat() {
        //Call the eatfood method and validate the selection
        try{
            game.EatFood(ItemTable.getSelectionModel().getSelectedIndex());
        }
        catch (ItemIsZero | ItemNotSelected msg) {
            ShowInfoMsg(msg.getMessage());
        }
        //Clear the item table
        ItemTable.getItems().clear();
        //Refresh the item table
        LoadItemTable();
    }
}