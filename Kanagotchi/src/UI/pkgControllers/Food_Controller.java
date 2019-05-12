package UI.pkgControllers;

import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.ItemIsZero;
import Engine.pkgExceptions.SaveFileDoesntExists;
import Engine.pkgItems.Items;
import Engine.pkgItems.ItemsObtained;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class Food_Controller extends IngameController {

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


    public Food_Controller() throws IOException, BadHeaderSave, SaveFileDoesntExists {

    }

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
        for(int i = 0; i < IngameController.game.getItem().getItemList().size(); i++) {
            Items item = (Items) IngameController.game.getItem().getItemList().get(i);
            ItemTable.getItems().add(new ItemsObtained(game.getItemsOwned().get(i), item.getName(), item.getHealthRecovered(), item.getExperience()));
        }
    }

    @FXML
    private void Eat() throws ItemIsZero {
        game.EatFood(ItemTable.getSelectionModel().getSelectedIndex());
        ItemTable.refresh();
    }

    @FXML
    public void Return() {
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }
}