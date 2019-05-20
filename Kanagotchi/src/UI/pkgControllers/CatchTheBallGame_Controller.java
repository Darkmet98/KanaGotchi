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

    @FXML
    Label time;
    @FXML
    Sphere sphere;

    //Mechanics
    private double dx = 7; //Step on x or velocity
    private double dy = 3; //Step on y

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {
        //move the ball
        sphere.setLayoutX(sphere.getLayoutX() + dx);
        sphere.setLayoutY(sphere.getLayoutY() + dy);

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
    @FXML
    public void initialize() {
        game.CatchBall.StartGame();
        LoadMiniGameListeners();
        LoadGameValues();
        SelectMiniGame_Controller.AddSphereColor(sphere);
        MoveBall();
    }


    public void LoadGameValues() {
        Punctuation.setText(String.valueOf(game.CatchBall.CommonValues.getMaxPunctuation()));
        MaxPunctuation.setText(String.valueOf(game.getMaxPunctuationCatchBall()));
        time.setText(String.valueOf(game.CatchBall.getTimeIngame()));
    }

    @FXML
    public void Quit() {
        ShowInfoMsg("Fin de la partida. Has conseguido " + (game.CatchBall.CommonValues.getMaxPunctuation()*2) + " monedas.");
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }

    @FXML
    public void BallCatched() {
        game.CatchBall.BallCatched();
        dx+=4;
        dy+=2;
        StopBall();
        MoveBall();
    }


    public void LoadMiniGameListeners() {
        game.CatchBall.getTimeIngameProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            time.setText(String.valueOf(game.CatchBall.getTimeIngame()));
        }));

        //Get the actual punctuation
        game.CatchBall.CommonValues.getMaxPunctuationProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            Punctuation.setText(String.valueOf(game.CatchBall.CommonValues.getMaxPunctuation()));
        }));
        //Get the life
        game.CatchBall.CommonValues.getLifeProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::LifeHearts));
    }

    private void MoveBall() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void StopBall() {
        timeline.stop();
    }

    public void LifeHearts() {
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
