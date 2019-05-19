package UI.pkgControllers;

import Engine.pkgExceptions.OperationResultIsNull;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MathGame_Controller extends Common_Controller {

    @FXML
    Label Punctuation;
    @FXML
    Label MaxPunctuation;
    @FXML
    Label Operation;
    @FXML
    Label OperationLine1;
    @FXML
    Label OperationLine2;
    @FXML
    ImageView Heart1;
    @FXML
    ImageView Heart2;
    @FXML
    ImageView Heart3;
    @FXML
    TextField UserResult;

    @FXML
    public void initialize() {
        game.Maths.StartGame();
        LoadMiniGameListeners();
        LoadGameValues();
    }

    @FXML
    public void CheckResult() {
        try {
            SendResults();
        } catch (OperationResultIsNull msg) {
            ShowInfoMsg(msg.getMessage());
        }


    }

    private void SendResults() throws OperationResultIsNull {
        if(UserResult.getText().isEmpty()) throw new OperationResultIsNull("Error, no puedes enviar el resultado en blanco.");
        game.Maths.CheckResult(Long.valueOf(UserResult.getText()));
        if(!game.Maths.isValueCorrect()) ShowInfoMsg("Error, el resultado era " + game.Maths.getResult() + ".");
        UserResult.clear();
    }

    @FXML
    public void Quit() {
        ShowInfoMsg("Fin de la partida. Has conseguido " + (game.Maths.getMaxPunctuation()*2) + " monedas.");
        VistaNavigator.loadVista(VistaNavigator.MAIN_INGAME);
    }

    private void LoadGameValues() {
        Punctuation.setText(String.valueOf(game.Maths.getMaxPunctuation()));
        MaxPunctuation.setText(String.valueOf(game.getMaxPunctuationMath()));
        ActualOperation();
        OperationLine1.setText(String.valueOf(game.Maths.getFirstLine()));
        OperationLine2.setText(String.valueOf(game.Maths.getSecondLine()));

    }

    private void LoadMiniGameListeners() {
        //Get the actual punctuation
        game.Maths.getMaxPunctuationProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            Punctuation.setText(String.valueOf(game.Maths.getMaxPunctuation()));
        }));
        //Get the life
        game.Maths.getLifeProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::LifeHearts));
        //Get the Actual Operation
        game.Maths.getTypeProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::ActualOperation));
        //Get the first line from the operation
        game.Maths.getFirstLineProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            OperationLine1.setText(String.valueOf(game.Maths.getFirstLine()));
        }));
        //Get the second line from the operation
        game.Maths.getSecondLineProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            OperationLine2.setText(String.valueOf(game.Maths.getSecondLine()));
        }));
        // force the field to be numeric only
        UserResult.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                UserResult.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void ActualOperation() {
        switch (game.Maths.getType()) {
            case 0: //Sumar
                Operation.setText("Sumar");
                break;
            case 1: //Restar
                Operation.setText("Restar");
                break;
            case 2: //Multiplicar
                Operation.setText("Multiplicar");
                break;
        }
    }

    public void LifeHearts() {
        switch (game.Maths.getLife()) {
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
