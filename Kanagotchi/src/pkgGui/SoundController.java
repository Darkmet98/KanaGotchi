package pkgGui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {

    //MediaPlayer for background Music
    MediaPlayer background;

    //MediaPlayer for button sounds
    MediaPlayer button;

    public void BackgroundMusic(String file) {
        try  {
            Media mediaBackground = new Media(getClass().getResource(file).toURI().toString());
            background = new MediaPlayer(mediaBackground);
            background.setCycleCount(MediaPlayer.INDEFINITE);
            background.setAutoPlay(true);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void InterfaceSounds(String file) {
        try  {
            Media mediaSound = new Media(getClass().getResource(file).toURI().toString());
            button = new MediaPlayer(mediaSound);
            button.setAutoPlay(true);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
