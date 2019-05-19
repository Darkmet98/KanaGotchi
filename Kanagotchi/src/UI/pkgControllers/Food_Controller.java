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

    @FXML
    public void initialize() {
        SetTable();
        LoadItemTable();
        LoadValues();
    }

    private void SetTable() {
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Health.setCellValueFactory(new PropertyValueFactory<>("Health"));
        Experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
    }

    private void LoadItemTable() {
        for(int i = 0; i < Ingame_Controller.game.getItem().getItemList().size(); i++) {
            Items item = (Items) Ingame_Controller.game.getItem().getItemList().get(i);
            ItemTable.getItems().add(new ItemsObtained(game.getItemsOwned().get(i), item.getName(), item.getHealthRecovered(), item.getExperience()));
        }
    }

    @FXML
    private void Eat() {
        try{
            game.EatFood(ItemTable.getSelectionModel().getSelectedIndex());
        }
        catch (ItemIsZero | ItemNotSelected msg) {
            ShowInfoMsg(msg.getMessage());
        }
        ItemTable.getItems().clear();
        LoadItemTable();
    }
}