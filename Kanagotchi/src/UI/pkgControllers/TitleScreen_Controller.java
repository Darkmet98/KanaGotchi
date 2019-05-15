package UI.pkgControllers;

import Engine.pkgMechanics.Sound;
import Engine.pkgSaves.WriteSave;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


public class TitleScreen_Controller extends Common_Controller {

    private static Sound Sounds = new Sound();

    /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

    @FXML
    private void NewGame() {
        VistaNavigator.loadVista(VistaNavigator.CHARA_MENU);
    }

    //Load the save and start the game
    @FXML
    private void LoadGame() {
        PressedSound();
        getSounds().getBackground().stop();
        getSounds().BackgroundMusic("/Media/BGM/BGM_Ingame.wav");
        VistaNavigator.loadSave = true;
        //Load the game screen
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);

    }

    //Go to the Settings page
    @FXML
    public void Settings() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.SETTINGS);
    }

    //Exit the program
    @FXML
    private void Exit() {
        PressedSound();
        Platform.exit();
    }
    //Start Background Music
    public static void StartMusic() { getSounds().BackgroundMusic("/Media/BGM/BGM_TitleScreen.wav"); }

    public static Sound getSounds() {
        return Sounds;
    }

}
