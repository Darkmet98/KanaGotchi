package UI.pkgControllers;

import Engine.pkgMechanics.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static UI.pkgControllers.TitleScreen_Controller.getSounds;

public class Common_Controller {

    public static Game game = new Game();
    @FXML
    public ProgressBar health;
    @FXML
    public ProgressBar experience;
    @FXML
    public Label status;
    @FXML
    public Label level;
    @FXML
    public ImageView character;
    @FXML
    public ImageView face;
    @FXML
    public AnchorPane window;

    public void LoadValues() {
        TextStatus();
        LoadListeners();
        double value = game.getHealth();
        health.setProgress(value/100);
        value = game.getExperience();
        experience.setProgress(value/100);
        level.setText(String.valueOf(game.getPlayerLevel()));
    }

    public void LoadListeners() {
        //Health Listener
        game.getHealthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double healthvalue = game.getHealth();
            health.setProgress(healthvalue/100);
        }));
        //Status Listener
        game.getStatusProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::TextStatus));
        //Experience Listener
        game.getExperienceProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double experiencevalue = game.getExperience();
            experience.setProgress(experiencevalue/100);
        }));
        //Level Listener
        game.getPlayerLevelProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            level.setText(String.valueOf(game.getPlayerLevel()));
        }));
    }

    //Sounds methods
    @FXML
    public void SelectedSound() {getSounds().InterfaceSounds("/Media/Sounds/Selection.wav");}
    @FXML
    public void PressedSound() {getSounds().InterfaceSounds("/Media/Sounds/Click.wav");}

    //Change the status
    public void TextStatus() {
        String result = "";
        switch (game.getStatus()) {
            case 0:
                result = "Muy enfadada";
                break;
            case 1:
                result = "Mosqueada";
                break;
            case 2:
                result = "Normal";
                break;
            case 3:
                result = "Feliz";
                break;
            case 4:
                result = "Super feliz";
                break;
        }
        status.setText(result);
    }

    public void DigiEvolution() {
        switch (game.getPlayerLevel()){
            case 1:
                if(game.getCharacterSelected() == 0) {
                    character.setFitHeight(100.0*3);
                    character.setFitWidth(280.0*3);
                    character.setLayoutX(336);
                    character.setLayoutY(127);
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_1.png").toString()));
                }
                else {
                    character.setFitHeight(109.0*3);
                    character.setFitWidth(339.0*3);
                    character.setLayoutX(345);
                    character.setLayoutY(125);
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_1.png").toString()));
                }
                break;
            case 2:
                if(game.getCharacterSelected() == 0) {

                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_2.png").toString()));
                }
                else {
                    character.setFitHeight(135.0*3);
                    character.setFitWidth(350.0*3);
                    character.setLayoutX(320);
                    character.setLayoutY(122);
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_2.png").toString()));
                }
                break;
            case 3:
                if(game.getCharacterSelected() == 0)  {
                    character.setFitHeight(140.0*3);
                    character.setFitWidth(355.0*3);
                    character.setLayoutX(316);
                    character.setLayoutY(124);
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_3.png").toString()));
                }
                else {
                    character.setFitHeight(140.0*3);
                    character.setFitWidth(355.0*3);
                    character.setLayoutX(325);
                    character.setLayoutY(115);
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_3.png").toString()));
                }
                break;
        }
    }

    public void ShowInfoMsg(String msg) {
        window.setEffect(new GaussianBlur());

        VBox MsgWindow = new VBox(5);
        MsgWindow.getChildren().add(new Label(msg));
        MsgWindow.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        MsgWindow.setAlignment(Pos.CENTER);
        MsgWindow.setPadding(new Insets(20));


        Button resume = new Button("Aceptar");
        MsgWindow.getChildren().add(resume);

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Main_Controller.stagex);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(MsgWindow, Color.TRANSPARENT));


        resume.setOnAction(event -> {
            window.setEffect(null);
            popupStage.hide();
        });

        popupStage.show();
    }

    @FXML
    public void Return() {
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }

}
