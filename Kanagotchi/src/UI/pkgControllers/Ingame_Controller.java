package UI.pkgControllers;

import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.SaveFileDoesntExists;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Ingame_Controller extends Common_Controller {

    @FXML
    private Label characterTalk;
    @FXML
    private ImageView dialogBox;

    //Start the game
    public Ingame_Controller() throws IOException, BadHeaderSave, SaveFileDoesntExists {
        if(VistaNavigator.loadSave && !game.isEngineStarted()) {
            game.load();
        }
        else if (!VistaNavigator.loadSave && !game.isEngineStarted()) {
            game.NewGame(false);
            game.setCharacterSelected(VistaNavigator.chara);
        }
    }

    //Initialize the game
    @FXML
    public void initialize() {
        LoadValues();
        DigiEvolution();
        if(game.getCharacterSelected() == 0) window.setId("IngameNeptune");
        else window.setId("IngameNoire");
    }

    @FXML
    public void Shop() {
        VistaNavigator.loadVista(VistaNavigator.SHOP);
    }

    @FXML
    public void Food() {
        VistaNavigator.loadVista(VistaNavigator.FOOD_MENU);
    }

    @FXML
    public void Menu() throws IOException {
    game.save();
    PressedSound();
    TitleScreen_Controller.getSounds().getBackground().stop();
    TitleScreen_Controller.StartMusic();
    game.Stop();
    game.setEngineStarted(false);
    VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    @Override
    public void TextStatus() {
        super.TextStatus();
        String chara;
        if (game.getCharacterSelected() == 0) chara ="Neptune";
        else chara = "Noire";
        switch (game.getStatus()) {
            case 0:
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/VeryBad.png").toString()));
                break;
            case 1:
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Bad.png").toString()));
                break;
            case 2:
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Normal.png").toString()));
                break;
            case 3:
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Happy.png").toString()));
                break;
            case 4:
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/VeryHappy.png").toString()));
                break;
        }
    }

    @FXML
    private void CharaAnimation() {
        TranslateTransition chara = new TranslateTransition(Duration.seconds(1), character);
        TranslateTransition charaface = new TranslateTransition(Duration.seconds(1), face);
        chara.setFromX(character.getX());
        chara.setCycleCount(2);
        chara.setAutoReverse(true);
        charaface.setFromX(face.getX());
        charaface.setCycleCount(2);
        charaface.setAutoReverse(true);

        //Generate a aleatory sequence of movements
        Random r = new Random();
        int random = r.nextInt(2);
        if(random == 1) {
            chara.setToX(310);
            charaface.setToX(310);
        }
        else {
            chara.setToX(-310);
            charaface.setToX(-310);
        }

        chara.play();
        charaface.play();
    }

    @FXML
    public void SayHello() {
        PauseTransition hideText = new PauseTransition(Duration.seconds(2));
        PauseTransition hideDialog = new PauseTransition(Duration.seconds(2));
        dialogBox.setVisible(true);
        characterTalk.setVisible(true);
        Random r = new Random();
        int random = r.nextInt(5);

        switch (random) {
            case 0:
                characterTalk.setText("¡Hola!");
                break;
            case 1:
                characterTalk.setText("¿Qué tal?");
                break;
            case 2:
                characterTalk.setText("¿Cómo te\n encuentras?");
                break;
            case 3:
                characterTalk.setText("¡Qué buen\n día hace!");
                break;
            case 4:
                characterTalk.setText("¡Qué hambre!");
                break;
        }
        // hide the dialog box if pause gets to end:
        hideText.setOnFinished(e -> dialogBox.setVisible(false));

        // hide the text if pause gets to end:
        hideDialog.setOnFinished(e -> characterTalk.setVisible(false));
        // start "timers":
        hideText.play();
        hideDialog.play();
    }
}
