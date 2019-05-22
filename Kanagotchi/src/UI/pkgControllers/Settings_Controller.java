package UI.pkgControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class Settings_Controller extends Common_Controller{

    //Values
    @FXML
    private Slider VolumenSlider;

    /*
    * Return to the title screen
     */
    @FXML
    private void TitleScreen() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    /*
    * Change the Actual Volume
     */
    @FXML
    public void ChangeVolume() {
        TitleScreen_Controller.getSounds().ChangeVolume(VolumenSlider.getValue()/100);
        //System.out.println(VolumenSlider.getValue()/100);
        //TitleScreen_Controller.getSounds().getBackground().setVolume(VolumenSlider.getValue()/100);
    }

}
