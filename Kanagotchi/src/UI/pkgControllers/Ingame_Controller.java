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



    //Values
    @FXML
    private Label characterTalk;
    @FXML
    private ImageView dialogBox;

    //Detect if the local save file doesn't exist
    boolean SaveDoesntExist = false;

    /*
    * Initialize the class
     */
    public Ingame_Controller() throws IOException{
        //Try to load the save
        if(VistaNavigator.loadSave && !game.isEngineStarted()) {
            try {
                game.load();
            }
            catch (SaveFileDoesntExists | BadHeaderSave s) {
                SaveDoesntExist = true;
            }
        }
        //If load correctly
        else if (!VistaNavigator.loadSave && !game.isEngineStarted()) {
            game.NewGame(false);
            game.setCharacterSelected(VistaNavigator.chara);
        }
    }

    /*
     * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Error in the DB but load correctly the local save
        if(game.isBdFailed() && !SaveDoesntExist) {
            ShowInfoMsg("Aviso, se ha producido un error al intentar acceder a la base de datos\n" +
                    "pero se ha cargado la partida local.");
            game.setBdFailed(false);
        }
        //Error in the DB and the local save file
        else if(game.isBdFailed() && SaveDoesntExist) {
            ShowInfoMsg("Aviso, se ha producido un error al intentar acceder a la base de datos\n" +
                    "y la partida local guardada está dañada o no existe.\n" +
                    "Has vuelto a la pantalla de título.");
            returnToTitleScreen();
            return;
        }

        //Load the values
        LoadValues();
        DigiEvolution();
        //Read the character selected
        if(game.getCharacterSelected() == 0) window.setId("IngameNeptune");
        else window.setId("IngameNoire");

        //Force the close request to end all timers
        Main_Controller.MainStage.setOnCloseRequest(we -> returnToTitleScreen());
    }

    /*
    * Open the MiniGame menu
     */
    @FXML
    public void SelectMiniGame() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.MINIGAME_MENU);}

    /*
    * Open the Shop menu
     */
    @FXML
    public void Shop() {

        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.SHOP);
    }

    /*
    * Open the food menu
     */
    @FXML
    public void Food() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.FOOD_MENU);
    }

    /*
    * Return to the title screen
     */
    @FXML
    public void Menu() throws IOException {
        //Save the game
        game.save();
        PressedSound();
        returnToTitleScreen();
        //Error on the DB
        if(game.isBdFailed()) {
            ShowInfoMsg("Aviso, se ha producido un error al intentar acceder a la base de datos\n" +
                    "pero la partida local se ha guardado sin problemas.");
        }
    }

    /*
    * Show the character status
     */
    @Override
    public void TextStatus() {
        super.TextStatus();
        String chara; //Set this for the UI Folder
        if (game.getCharacterSelected() == 0) chara ="Neptune";
        else chara = "Noire";
        switch (game.getStatus()) {
            case 0: //Very bad
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/VeryBad.png").toString()));
                break;
            case 1: //Bad
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Bad.png").toString()));
                break;
            case 2: //Normal
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Normal.png").toString()));
                break;
            case 3: //Happy
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/Happy.png").toString()));
                break;
            case 4: //Very happy
                face.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/" + chara +"/Faces/VeryHappy.png").toString()));
                break;
        }
    }

    /*
    * Move the face and body character on the screen
     */
    @FXML
    private void CharaAnimation() {
        //Add a transition on the character body
        TranslateTransition chara = new TranslateTransition(Duration.seconds(1), character);
        //Add a transition on the character face
        TranslateTransition charaface = new TranslateTransition(Duration.seconds(1), face);
        chara.setFromX(character.getX()); //Get the X position from the body
        chara.setCycleCount(2); //Move two times
        chara.setAutoReverse(true); //Return to the original position
        charaface.setFromX(face.getX()); //Get the X position from the face
        charaface.setCycleCount(2); //Move two times
        charaface.setAutoReverse(true); //Return to the original position

        //Generate a aleatory sequence of movements
        Random r = new Random();
        int random = r.nextInt(2);
        if(random == 1) {
            chara.setToX(310); //Move the body to the right border from the center
            charaface.setToX(310); //Move the face the right border from the center
        }
        else {
            chara.setToX(-310);  //Move the body to the left border from the center
            charaface.setToX(-310);  //Move the face to the left border from the center
        }

        chara.play(); //Play the body animation
        charaface.play(); //Play the face animation
    }

    /*
    * Draw text on the screen
     */
    @FXML
    public void SayHello() {
        PauseTransition hideText = new PauseTransition(Duration.seconds(2)); //Add a text transition
        PauseTransition hideDialog = new PauseTransition(Duration.seconds(2)); //Add a image transition
        dialogBox.setVisible(true); //Set visible the text
        characterTalk.setVisible(true); //Set visible the image

        //Generate a random int with 5 options
        Random r = new Random();
        int random = r.nextInt(5);

        switch (random) {
            case 0: //Case 1
                characterTalk.setText("¡Hola!");
                break;
            case 1: //Case 2
                characterTalk.setText("¿Qué tal?");
                break;
            case 2: //Case 3
                characterTalk.setText("¿Cómo te\n encuentras?");
                break;
            case 3: //Case 4
                characterTalk.setText("¡Qué buen\n día hace!");
                break;
            case 4: //Case 5
                characterTalk.setText("¡Qué hambre!");
                break;
        }
        //Hide the dialog box if pause gets to end
        hideText.setOnFinished(e -> dialogBox.setVisible(false));

        //Hide the text if pause gets to end
        hideDialog.setOnFinished(e -> characterTalk.setVisible(false));

        //Start timers
        hideText.play();
        hideDialog.play();
    }
}
