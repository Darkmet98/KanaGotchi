package pkgGui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {

    //MediaPlayer for background Music
    public static MediaPlayer background;

    //MediaPlayer for button sounds
    private MediaPlayer button;

    public void BackgroundMusic(String file) {
        //Settings link

        try  {
            Media mediaBackground = new Media(getClass().getResource(file).toURI().toString());
            setBackground(new MediaPlayer(mediaBackground));
            getBackground().setCycleCount(MediaPlayer.INDEFINITE);
            getBackground().setAutoPlay(true);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void InterfaceSounds(String file) {
        try  {
            Media mediaSound = new Media(getClass().getResource(file).toURI().toString());
            setButton(new MediaPlayer(mediaSound));
            getButton().setAutoPlay(true);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public MediaPlayer getBackground() {
        return background;
    }

    public void setBackground(MediaPlayer background) {
        this.background = background;
    }

    public MediaPlayer getButton() {
        return button;
    }

    public void setButton(MediaPlayer button) {
        this.button = button;
    }
}
