package UI.pkgControllers;

import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgItems.Items;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Shop_Controller {

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




    @FXML
    public void initialize() {
        if(Money.getText().equals("VALUE")) {
            SetTable();
            LoadListeners();
            LoadItemTable();
        }
    }

    private void LoadItemTable() {
        for(int i = 0; i < IngameController.game.getItem().getItemList().size(); i++) {
            Items item = (Items) IngameController.game.getItem().getItemList().get(i);
            ItemTable.getItems().add(item);
        }
    }

    private void SetTable() {
            Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            Health.setCellValueFactory(new PropertyValueFactory<>("HealthRecovered"));
            Experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
    }

    private void LoadListeners() {
        Money.setText(String.valueOf(IngameController.game.getMoney()));
        //Money Listener
        IngameController.game.getMoneyProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> Money.setText(String.valueOf(IngameController.game.getMoney()))));
    }

    @FXML
    public void Buy() throws InsufficientMoney {
        IngameController.game.Buy(ItemTable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void Return() {
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }
}
