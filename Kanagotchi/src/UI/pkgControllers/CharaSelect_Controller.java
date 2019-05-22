package UI.pkgControllers;

import Engine.pkgSaves.WriteSave;
import javafx.fxml.FXML;

import static UI.pkgControllers.TitleScreen_Controller.getSounds;

public class CharaSelect_Controller extends Common_Controller {

    /*
     * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Check if the local save exists
        if(WriteSave.SaveFile.exists()) ShowInfoMsg("Aviso, ya tienes una partída previamente comenzada,\n" +
                "si seleccionas un personaje, sobrescribirás la partida guardada.\nSi no quieres sobrescribir la partida, " +
                "selecciona «Volver».");
    }

    /*
    * Select Noire character
     */
    @FXML
    public void Noire() {
        VistaNavigator.chara = 1;
        StartGame();
    }

    /*
     * Select Neptune character
     */
    @FXML
    public void Neptune() {
        VistaNavigator.chara = 0;
        StartGame();
    }

    /*
     * Return to the title screen
     */
    @FXML
    private void ReturnToTitleScreen() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);
    }

    /*
    * Start the game
     */
    private void StartGame() {
        PressedSound();
        getSounds().getBackground().stop();
        getSounds().BackgroundMusic("/Media/BGM/BGM_Ingame.wav");
        VistaNavigator.loadSave = false;
        //Load the game screen
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }
}
