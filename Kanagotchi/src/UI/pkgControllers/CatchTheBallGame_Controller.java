package UI.pkgControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class CatchTheBallGame_Controller extends MiniGameCommon_Controller {

    //JavaFX Items
    @FXML
    Label time;
    @FXML
    Sphere sphere;

    //Ball Mechanics
    private double dx = 7; //Step on x or velocity
    private double dy = 3; //Step on y

    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {
        //move the ball
        sphere.setLayoutX(sphere.getLayoutX() + dx);
        sphere.setLayoutY(sphere.getLayoutY() + dy);

        //Obtain the limits on the window
        Bounds bounds = window.getBoundsInLocal();

        //If the ball reaches the left or right border make the step negative
        if (sphere.getLayoutX() <= (bounds.getMinX() + sphere.getRadius()) ||
                sphere.getLayoutX() >= (bounds.getMaxX() - sphere.getRadius())) {

            dx = -dx;

        }

        //If the ball reaches the bottom or top border make the step negative
        if ((sphere.getLayoutY() >= (bounds.getMaxY() - sphere.getRadius())) ||
                (sphere.getLayoutY() <= (bounds.getMinY() + sphere.getRadius()))) {

            dy = -dy;

        }

    }));



    /*
    * Initialize the FXML Controller
     */
    @FXML
    public void initialize() {
        //Start the game
        game.CatchBall.StartGame();
        //Start the listeners
        LoadMiniGameListeners();
        //Load the values
        LoadGameValues();
        //Add the textures to the ball and move
        SelectMiniGame_Controller.AddSphereColor(sphere);
        MoveBall();
    }


    /*
    * Load the game Values
     */
    private void LoadGameValues() {
        //Load the punctuation
        Punctuation.setText(String.valueOf(game.CatchBall.CommonValues.getMaxPunctuation()));
        //Read the max punctuation
        MaxPunctuation.setText(String.valueOf(game.getMaxPunctuationCatchBall()));
        //Get the time
        time.setText(String.valueOf(game.CatchBall.getTimeIngame()));
    }

    /*
    * Quit the game
     */
    @FXML
    public void Quit() {
        PressedSound();
        //Show a mesagge
        ShowInfoMsg("Fin de la partida. Has conseguido " + (game.CatchBall.CommonValues.getMaxPunctuation()*2) + " monedas y\n" + game.CatchBall.CommonValues.getMaxPunctuation() + " puntos de experiencia.");
        //End the game and go to the ingame screen
        game.CatchBall.EndGame();
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }

    /*
    * When you catch the ball
     */
    @FXML
    public void BallCatched() {
        PressedSound();
        //Add the punctuation and rewrite the time
        game.CatchBall.BallCatched();
        //Add more speed
        dx+=4;
        dy+=2;
        //Reload the ball
        StopBall();
        MoveBall();
    }


    /*
    * Load the listeners
     */
    private void LoadMiniGameListeners() {
        //Load the time
        game.CatchBall.getTimeIngameProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> time.setText(String.valueOf(game.CatchBall.getTimeIngame()))));

        //Get the actual punctuation
        game.CatchBall.CommonValues.getMaxPunctuationProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> Punctuation.setText(String.valueOf(game.CatchBall.CommonValues.getMaxPunctuation()))));

        //Get the life
        game.CatchBall.CommonValues.getLifeProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::LifeHearts));
    }

    /*
    * Move the ball
     */
    private void MoveBall() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /*
    * Stop the ball
     */
    private void StopBall() {
        timeline.stop();
    }

    /*
    * Get the life info
     */
    private void LifeHearts() {
        switch (game.CatchBall.CommonValues.getLife()) {
            case 0: //End game
                Heart1.setVisible(false);
                Heart2.setVisible(false);
                Heart3.setVisible(false);
                Quit();
                break;
            case 1: //One life
                Heart1.setVisible(true);
                Heart2.setVisible(false);
                Heart3.setVisible(false);

                break;
            case 2: //Two lifes
                Heart1.setVisible(true);
                Heart2.setVisible(true);
                Heart3.setVisible(false);
                break;
            case 3: //Three lifes
                Heart1.setVisible(true);
                Heart2.setVisible(true);
                Heart3.setVisible(true);
                break;
        }
    }

}
