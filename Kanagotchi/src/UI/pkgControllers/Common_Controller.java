package UI.pkgControllers;

import Engine.pkgMechanics.Game;
import Engine.pkgMechanics.Sound;
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


    //Values
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

    /*
    * Load the game values
     */
    public void LoadValues() {
        //Read the text status
        TextStatus();
        //Execute the listeners
        LoadListeners();
        //Read the health
        double value = game.getHealth();
        health.setProgress(value/100);
        //Read the experience
        value = game.getExperience();
        experience.setProgress(value/100);
        //Read the level
        level.setText(String.valueOf(game.getPlayerLevel()));
    }

    /*
    * Execute the listeners
     */
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
        game.getPlayerLevelProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> level.setText(String.valueOf(game.getPlayerLevel()))));
    }

    /*
    * Sounds methods
     */
    @FXML
    public void SelectedSound() {getSounds().InterfaceSounds(Sound.SELECTED);}
    @FXML
    public void PressedSound() {getSounds().InterfaceSounds(Sound.CLICKED);}

    /*
    * Change the status
     */
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

    /*
    * Change the character body
     */
    public void DigiEvolution() {
        switch (game.getPlayerLevel()){
            case 1: //Children
                if(game.getCharacterSelected() == 0) { //Neptune
                    character.setFitHeight(100.0*3); //Set the height
                    character.setFitWidth(280.0*3); //Set the width
                    character.setLayoutX(336); //Set the X position
                    character.setLayoutY(127); //Set the Y position
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_1.png").toString())); //Load the image
                }
                else { //Noire
                    character.setFitHeight(109.0*3);  //Set the height
                    character.setFitWidth(339.0*3); //Set the width
                    character.setLayoutX(345); //Set the X position
                    character.setLayoutY(125); //Set the Y position
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_1.png").toString())); //Load the image
                }
                break;
            case 2: //Young
                if(game.getCharacterSelected() == 0) { //Neptune
                    //In this phase the layout and size from the image is loaded from the FXML
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_2.png").toString())); //Load the image
                }
                else { //Noire
                    character.setFitHeight(135.0*3); //Set the height
                    character.setFitWidth(350.0*3); //Set the width
                    character.setLayoutX(320); //Set the X position
                    character.setLayoutY(122); //Set the Y position
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_2.png").toString())); //Load the image
                }
                break;
            case 3: //Adult
                if(game.getCharacterSelected() == 0)  { //Neptune
                    character.setFitHeight(140.0*3); //Set the height
                    character.setFitWidth(355.0*3); //Set the width
                    character.setLayoutX(316); //Set the X position
                    character.setLayoutY(124); //Set the Y position
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Neptune/Nep_3.png").toString())); //Load the image
                }
                else { //Noire
                    character.setFitHeight(140.0*3); //Set the height
                    character.setFitWidth(355.0*3); //Set the width
                    character.setLayoutX(325); //Set the X position
                    character.setLayoutY(115); //Set the Y position
                    character.setImage(new Image(this.getClass().getResource("/Media/Images/Characters/Noire/Noire_3.png").toString())); //Load the image
                }
                break;
        }
    }

    /*
    * Show a info message
     */
    public void ShowInfoMsg(String msg) {
        //Set a blurred effect
        window.setEffect(new GaussianBlur());

        //Create a new MSG box
        VBox MsgWindow = new VBox(5);
        MsgWindow.getChildren().add(new Label(msg));
        MsgWindow.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        MsgWindow.setAlignment(Pos.CENTER);
        MsgWindow.setPadding(new Insets(20));


        //Create a new button
        Button resume = new Button("Aceptar");
        MsgWindow.getChildren().add(resume);

        //Create a new stage to show on the actual screen
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Main_Controller.MainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(MsgWindow, Color.TRANSPARENT));


        //Set a action on the button
        resume.setOnAction(event -> {
            window.setEffect(null);
            popupStage.hide();
        });

        //Show
        popupStage.show();
    }

    /*
    * Return to the ingame screen
     */
    @FXML
    public void Return() {
        PressedSound();
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }

}
