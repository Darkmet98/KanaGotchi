package Engine.pkgGames;

import Engine.pkgExceptions.OperationResultIsNull;
import Engine.pkgMechanics.Game;
import javafx.beans.property.LongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class MathMotor {

    //Values
    private IntegerProperty Type;
    private IntegerProperty FirstLine;
    private IntegerProperty SecondLine;
    private LongProperty Result;
    private LongProperty OldResult;
    private Random r = new Random();
    private boolean ValueCorrect = false;
    private Common CommonValues;

    /*
    * Initialize the motor
     */
    public MathMotor(Game game) {
        setCommonValues(new Common(game));
    }

    /*
    * Start the game
     */
    public void StartGame() {
        //Start the game
        getCommonValues().StartGame();
        //Get a new calculation
        NewCalculation();

    }

    /*
    * End the game
     */
    public void EndGame() {
        //If you have a better punctuation
        if (getCommonValues().getMaxPunctuation() >  getCommonValues().InGameValues.getMaxPunctuationMath())  getCommonValues().InGameValues.setMaxPunctuationMath(getCommonValues().getMaxPunctuation());
        //Close the engine
        getCommonValues().EndGame();
    }

    /*
    * Check the results
     */
    public void CheckResult(Long result) throws OperationResultIsNull {
        //Custom exception when you send a null result
        if(null == result) throw new OperationResultIsNull("Error, no puedes enviar el resultado en blanco.");
        //Check the result
        if(getResult().equals(result)) {
            setValueCorrect(true);
            getCommonValues().setMaxPunctuation(getCommonValues().getMaxPunctuation()+1);
        }
        else {
            setValueCorrect(false);
            setOldResult(getResult());
            getCommonValues().setLife(getCommonValues().getLife()-1);
        }

        //If you don't have more life
        if(getCommonValues().getLife() == 0) {
            EndGame();
        }
        else {
            NewCalculation();
        }
    }
    /*
    * Generate a new calc
     */
    private void NewCalculation() {
        //Get the numbers
        int calc1 = r.nextInt(99999);
        int calc2 = r.nextInt(99999);

        //If the second number are higher than the first
        if (calc1 < calc2) {
            setSecondLine(calc1);
            setFirstLine(calc2);
        }
        else {
            setSecondLine(calc2);
            setFirstLine(calc1);
        }

        //Set the operation type
        setType(r.nextInt(3));
        //Get the result
        GetTheResult();
    }

    /*
    * Get the result
     */
    private void GetTheResult() {
        switch (getType()) {
            case 0: //Sumar
                setResult((long)getFirstLine()+getSecondLine());
                break;
            case 1: //Restar
                setResult((long)getFirstLine()-getSecondLine());
                break;
            case 2: //Multiplicar
                setResult((long)getFirstLine()*getSecondLine());
                break;
        }
    }


    //Get Set Zone
    public IntegerProperty getTypeProperty() {
        return Type;
    }
    public Integer getType() {
        return Type.getValue();
    }

    /*
     * Type
     *
     * 0 = Sumar
     * 1 = Restar
     * 2 = Multiplicar
     *
     */
    private void setType(Integer type) {
        if(Type == null) Type = new SimpleIntegerProperty();
        Type.set(type);
    }

    public IntegerProperty getFirstLineProperty() {
        return FirstLine;
    }
    public int getFirstLine() {
        return FirstLine.getValue();
    }

    private void setFirstLine(int firstLine) {
        if(FirstLine == null) FirstLine = new SimpleIntegerProperty();
        FirstLine.set(firstLine);
    }

    public IntegerProperty getSecondLineProperty() {
        return SecondLine;
    }

    public int getSecondLine() {
        return SecondLine.getValue();
    }

    private void setSecondLine(int secondLine) {
        if(SecondLine == null) SecondLine = new SimpleIntegerProperty();
        SecondLine.set(secondLine);
    }

    public Long getResult() {
        return Result.getValue();
    }

    public void setResult(Long result) {
        if(Result == null) Result = new SimpleLongProperty();
        Result.set(result);
    }

    public Long getOldResult() {
        return OldResult.getValue();
    }

    public void setOldResult(Long oldresult) {
        if(OldResult == null) OldResult = new SimpleLongProperty();
        OldResult.set(oldresult);
    }

    public boolean isValueCorrect() {
        return ValueCorrect;
    }

    private void setValueCorrect(boolean valueCorrect) {
        ValueCorrect = valueCorrect;
    }

    public Common getCommonValues() {
        return CommonValues;
    }

    private void setCommonValues(Common commonValues) {
        CommonValues = commonValues;
    }
}
