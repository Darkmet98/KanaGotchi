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
    public ProgressBar experience;
    @FXML
    public Label status;
    @FXML
    public Label level;
    public static Game game = new Game();

    //Start the game
    public IngameController() throws IOException, BadHeaderSave, SaveFileDoesntExists {
        if(VistaNavigator.loadSave && !game.isEngineStarted()) {
            game.load();
        }
        else if (!VistaNavigator.loadSave && !game.isEngineStarted()) {
            game.NewGame(false);
        }
    }

    //Initialize the game
    @FXML
    public void initialize() {
        LoadValues();
    }
    public void LoadValues() {
        TextStatus();
        LoadListeners();
        double value = game.getHealth();
        health.setProgress(value/100);
        value = game.getExperience();
        experience.setProgress(value/100);
        level.setText(String.valueOf(game.getPlayerLevel()));
    }
    public void LoadListeners() {
        //Health Listener
        game.getHealthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double healthvalue = game.getHealth();
            health.setProgress(healthvalue/100);
        }));
        //Status Listener
        game.getStatusProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::TextStatus));
        //Experience Listener
        game.getExperienceProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double experiencevalue = game.getExperience();
            experience.setProgress(experiencevalue/100);
        }));
        //Level Listener
        game.getPlayerLevelProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            level.setText(String.valueOf(game.getPlayerLevel()));
        }));
    }

    @FXML
    public void Shop() {
        VistaNavigator.loadVista(VistaNavigator.SHOP);
    }

    @FXML
    public void Food() {
        VistaNavigator.loadVista(VistaNavigator.FOOD_MENU);
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
    game.setEngineStarted(false);
    VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    //Change the status
    public void TextStatus() {
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
