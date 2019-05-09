package UI.pkgControllers;

import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.SaveFileDoesntExists;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import Engine.pkgMechanics.Game;

import java.io.IOException;

public class IngameController {

    @FXML
    public ProgressBar health;
    @FXML
    public Label status;
    private Game game = new Game();

    //Start the game
    public IngameController() throws IOException, BadHeaderSave, SaveFileDoesntExists {
        if(VistaNavigator.loadSave) game.load();
        else game.NewGame(true);
    }

    //Initialize the game
    @FXML
    public void initialize() {
        LoadValues();
    }
    private void LoadValues() {
        TextStatus();
        LoadListeners();
        double healthvalue = game.Health.getValue();
        health.setProgress(healthvalue/100);
    }
    private void LoadListeners() {
        //Health Listener
        game.Health.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double healthvalue = game.Health.getValue();
            health.setProgress(healthvalue/100);
        }));
        //Status Listener
        game.Status.addListener((observable, oldValue, newValue) -> Platform.runLater(this::TextStatus));
    }


    //Sounds methods
    @FXML
    public void SelectedSound() {Controller.SelectedSound();}
    @FXML
    public void PressedSound() {Controller.PressedSound();}

    @FXML
    public void Menu() throws IOException {
    game.save();
    PressedSound();
    Controller.getSounds().getBackground().stop();
    Controller.StartMusic();
    game.Stop();
    VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    //Change the status
    private void TextStatus() {
        String result = "";
        switch (game.getStatus()) {
            case 0:
                result = "Muy enfadada";
                break;
            case 1:
                result = "Mosqueada";
                break;
            case 2:
                result = "Normal";
                break;
            case 3:
                result = "Feliz";
                break;
            case 4:
                result = "Super feliz";
                break;
        }
        status.setText(result);
    }

}
