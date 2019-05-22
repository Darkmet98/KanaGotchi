package Engine.pkgMechanics;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    //TODO Refactorizar esto y añadir el cambio de volumen cuando cambias la música

    //MediaPlayer for background Music
    public static MediaPlayer background;

    //MediaPlayer for button sounds
    private MediaPlayer button;

    /*
    * Load the background music
     */
    public void BackgroundMusic(String file) {
        //Settings link
        try  {
            Media mediaBackground = new Media(getClass().getResource(file).toURI().toString());
            setBackground(new MediaPlayer(mediaBackground));
            getBackground().setCycleCount(MediaPlayer.INDEFINITE);
            getBackground().setAutoPlay(true);
        }
        catch (Exception e) {
            //Enabled only for debug purposes
            //e.printStackTrace();
        }
    }

    /*
     * Load the sound effect
     */
    public void InterfaceSounds(String file) {
        try  {
            Media mediaSound = new Media(getClass().getResource(file).toURI().toString());
            setButton(new MediaPlayer(mediaSound));
            getButton().setAutoPlay(true);
        }
        catch (Exception e) {
            //Enabled only for debug purposes
            //e.printStackTrace();
        }
    }

    //Get Set Zone
    public MediaPlayer getBackground() {
        return background;
    }

    public void setBackground(MediaPlayer background) {
        Sound.background = background;
    }

    public MediaPlayer getButton() {
        return button;
    }

    public void setButton(MediaPlayer button) {
        this.button = button;
    }
}
