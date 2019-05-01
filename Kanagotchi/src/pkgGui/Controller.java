package pkgGui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;


public class Controller {

    //@FXML
    //private ChoiceBox;

    @FXML
    private Slider VolumenSlider;

    private SoundController Sounds = new SoundController();

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
    private void SelectedSound() {getSounds().InterfaceSounds("/Media/Sounds/Selection.wav");}

    //Pressed Button
    @FXML
    private void PressedSound() {getSounds().InterfaceSounds("/Media/Sounds/Click.wav");}

    //Go to the Settings page
    @FXML
    private void Settings() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.SETTINGS);
    }

    //Go to the Title Screen
    @FXML
    private void TitleScreen() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    //Exit the program
    @FXML
    private void Exit() {
        PressedSound();
        Platform.exit();
    }

    @FXML
    public void ChangeVolume() {
        System.out.println(VolumenSlider.getValue()/100);
        getSounds().getBackground().setVolume(VolumenSlider.getValue()/100);
    }

    public void StartMusic() {
        //Start Background Music
        getSounds().BackgroundMusic("/Media/BGM/BGM_TitleScreen.wav");
    }

    public SoundController getSounds() {
        return Sounds;
    }

    public void setSounds(SoundController sounds) {
        Sounds = sounds;
    }
}
