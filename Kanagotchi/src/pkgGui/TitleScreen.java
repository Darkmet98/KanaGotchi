package pkgGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleScreen extends Application {

    private SoundController Sounds = new SoundController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
        root.setId("pane");
        primaryStage.setTitle("Kanagotchi");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.sizeToScene();
        primaryStage.getScene().getStylesheets().addAll(this.getClass().getResource("css/TitleScreen.css").toExternalForm());
        primaryStage.getScene().getStylesheets().addAll(this.getClass().getResource("css/ImageButtons.css").toExternalForm());
        primaryStage.show();

        //Start Background Music
        getSounds().BackgroundMusic("/Media/BGM/BGM_TitleScreen.wav");
    }



    public static void main(String[] args) {
        launch(args);
    }

    public SoundController getSounds() {
        return Sounds;
    }

    public void setSounds(SoundController sounds) {
        Sounds = sounds;
    }
}
