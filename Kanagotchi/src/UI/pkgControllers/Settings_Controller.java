package UI.pkgControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class Settings_Controller extends Common_Controller{

    private int[][] Resolutions = new int[][] {{800, 600}};

    @FXML
    private Slider VolumenSlider;

    //Go to the Title Screen
    @FXML
    private void TitleScreen() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    @FXML
    public void ChangeVolume() {
        System.out.println(VolumenSlider.getValue()/100);
        TitleScreen_Controller.getSounds().getBackground().setVolume(VolumenSlider.getValue()/100);
    }

}
