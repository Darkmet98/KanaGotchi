package UI.pkgControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class Controller {

    //@FXML
    //private ChoiceBox;

    private static SoundController Sounds = new SoundController();

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

    //Selected Button
    @FXML
    public static void SelectedSound() {getSounds().InterfaceSounds("/Media/Sounds/Selection.wav");}
    public void SelectedSound(MouseEvent mouseEvent) {
        SelectedSound();
    }

    //Pressed Button
    @FXML
    public static void PressedSound() {getSounds().InterfaceSounds("/Media/Sounds/Click.wav");}
    public void PressedSound(MouseEvent mouseEvent) {
        PressedSound();
    }

    //Start the game
    @FXML
    private void StartGame() {
        PressedSound();
        getSounds().getBackground().stop();
        getSounds().BackgroundMusic("/Media/BGM/BGM_Ingame.wav");
        VistaNavigator.loadSave = false;
        //Load the game screen
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
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

    public static SoundController getSounds() {
        return Sounds;
    }

}
