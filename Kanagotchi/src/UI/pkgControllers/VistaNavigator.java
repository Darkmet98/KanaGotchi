//https://gist.github.com/jewelsea/6460130

package UI.pkgControllers;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class VistaNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN_INGAME = "/UI/pkgFxml/Main_Ingame.fxml";
    public static final String MAIN_WINDOW = "/UI/pkgFxml/MainWindow.fxml";
    public static final String TITLE_SCREEN = "/UI/pkgFxml/TitleScreen.fxml";
    public static final String SETTINGS = "/UI/pkgFxml/Settings.fxml";
    public static final String SHOP = "/UI/pkgFxml/Shop.fxml";
    public static final String FOOD_MENU = "/UI/pkgFxml/Food.fxml";
    public static final String CHARA_MENU = "/UI/pkgFxml/CharaSelect.fxml";
    public static final String MINIGAME_MENU = "/UI/pkgFxml/SelectMiniGame.fxml";

    //MiniGames
    public static final String MINIGAME_MATH = "/UI/pkgFxml/MathGame.fxml";

    /** The main application layout controller. */
    public static TitleScreen_Controller mainTitleScreenController;
    public static boolean loadSave = false;
    public static int chara = 0;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainTitleScreenController the main application layout controller.
     */
    public static void setMainTitleScreenController(TitleScreen_Controller mainTitleScreenController) {
        VistaNavigator.mainTitleScreenController = mainTitleScreenController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
            mainTitleScreenController.setVista(
                    FXMLLoader.load(
                            VistaNavigator.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
