package pkgGui;

import javafx.application.Platform;
import javafx.fxml.FXML;


public class Controller {

    private SoundController Sounds = new SoundController();

    // Add a public no-args constructor
    public Controller() {
    }

    @FXML
    private void initialize() {}

    //Exit the program
    @FXML
    private void Exit() {
        Platform.exit();
    }

    //Selected Button
    @FXML
    private void SelectedSound() {getSounds().InterfaceSounds("/Media/Sounds/Selection.wav");}

    //Pressed Button
    @FXML
    private void PressedSound() {getSounds().InterfaceSounds("/Media/Sounds/Click.wav");}

    public SoundController getSounds() {
        return Sounds;
    }

    public void setSounds(SoundController sounds) {
        Sounds = sounds;
    }
}
