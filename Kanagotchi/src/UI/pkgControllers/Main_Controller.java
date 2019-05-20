package UI.pkgControllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Controller extends Application {

    static Stage stagex;
    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("Kanagotchi");
        stage.setScene(createScene(loadMainPane()));
        stage.sizeToScene();
        /* Workaround Fix Linux */
        //stage.setWidth(800);
        //stage.setHeight(600);
        //stage.setResizable(false);
        stage.show();
        stagex = stage;
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
                getClass().getResourceAsStream(
                        VistaNavigator.MAIN_WINDOW
                )
        );

        TitleScreen_Controller titleScreenController = loader.getController();
        VistaNavigator.setMainTitleScreenController(titleScreenController);
        VistaNavigator.loadVista(VistaNavigator.TITLE_SCREEN);

        return mainPane;
    }


    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane, 800, 600
        );
        //Load the custom font
        Font.loadFont(this.getClass().getResource("/Media/Fonts/Curse Casual.ttf").toExternalForm(), 10);

        //Load the CSS
        scene.getStylesheets().addAll(this.getClass().getResource("../css/Window.css").toExternalForm());
        scene.getStylesheets().addAll(this.getClass().getResource("../css/ImageButtons.css").toExternalForm());
        scene.getStylesheets().addAll(this.getClass().getResource("../css/UI.css").toExternalForm());
        TitleScreen_Controller.StartMusic();
        return scene;
    }


    public static void main() {
        launch();
    }
}
