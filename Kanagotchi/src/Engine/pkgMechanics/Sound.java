package Engine.pkgMechanics;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    /* Audio files */

    //Background Music
    public static final String TITLE_SCREEN = "/Media/BGM/BGM_TitleScreen.wav";
    public static final String IN_GAME = "/Media/BGM/BGM_Ingame.wav";

    //Sound effects
    public static final String SELECTED = "/Media/Sounds/Selection.wav";
    public static final String CLICKED = "/Media/Sounds/Click.wav";

    //MediaPlayer for background Music
    private MediaPlayer background;
    //MediaPlayer for button sounds
    private MediaPlayer button;

    //Memory volume
    private double VolumeGeneral = 1;

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
            if(VolumeGeneral != 1) background.setVolume(VolumeGeneral);
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
            if(VolumeGeneral != 1) button.setVolume(VolumeGeneral);
        }
        catch (Exception e) {
            //Enabled only for debug purposes
            //e.printStackTrace();
        }
    }

    /*
    * Set the volume
     */
    public void ChangeVolume(double volume) {
        button.setVolume(volume);
        background.setVolume(volume);
        VolumeGeneral = volume;
    }

    //Get Set Zone
    public MediaPlayer getBackground() {
        return background;
    }
    public void setBackground(MediaPlayer Background) {
        background = Background;
    }
    public MediaPlayer getButton() {
        return button;
    }
    public void setButton(MediaPlayer button) {
        this.button = button;
    }
}
