package Engine.pkgGames.pkgMath.Engine;

import Engine.pkgExceptions.OperationResultIsNull;
import Engine.pkgMechanics.Game;
import javafx.beans.property.LongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class MathMotor {

    private IntegerProperty Type;
    private IntegerProperty FirstLine;
    private IntegerProperty SecondLine;
    private LongProperty Result;
    private LongProperty MaxPunctuation;
    private IntegerProperty Life;
    private Random r = new Random();
    private Game GameValues;
    private boolean ValueCorrect = false;

    public MathMotor(Game game) {
        GameValues = game;
    }

    /*
    * Type
    *
    * 0 = Sumar
    * 1 = Restar
    * 2 = Multiplicar
     */


    public void StartGame() {
        setLife(3);
        setMaxPunctuation((long) 0);
        NewCalculation();

    }

    public void CheckResult(Long result) throws OperationResultIsNull {
        if(result.equals(null)) throw new OperationResultIsNull("Error, no puedes enviar el resultado en blanco.");
        if(getResult().equals(result)) {
            setValueCorrect(true);
            setMaxPunctuation(getMaxPunctuation()+1);
        }
        else {
            setValueCorrect(false);
            setLife(getLife()-1);
        }

        if(getLife() == 0) {
            EndGame();
        }
        else {
            NewCalculation();
        }
    }

    public void NewCalculation() {
        int calc1 = r.nextInt(99999);
        int calc2 = r.nextInt(99999);
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

    public void EndGame() {
        if (getMaxPunctuation() > GameValues.getMaxPunctuationMath()) GameValues.setMaxPunctuationMath(getMaxPunctuation());
        GameValues.setMoney(GameValues.getMoney() + (getMaxPunctuation()*2));
    }


    //Get Set Zone
    public IntegerProperty getTypeProperty() {
        return Type;
    }
    public Integer getType() {
        return Type.getValue();
    }

    public void setType(Integer type) {
        if(Type == null) Type = new SimpleIntegerProperty();
        Type.set(type);
    }

    public IntegerProperty getFirstLineProperty() {
        return FirstLine;
    }
    public int getFirstLine() {
        return FirstLine.getValue();
    }

    public void setFirstLine(int firstLine) {
        if(FirstLine == null) FirstLine = new SimpleIntegerProperty();
        FirstLine.set(firstLine);
    }

    public IntegerProperty getSecondLineProperty() {
        return SecondLine;
    }

    public int getSecondLine() {
        return SecondLine.getValue();
    }

    public void setSecondLine(int secondLine) {
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

    public LongProperty getMaxPunctuationProperty() {
        return MaxPunctuation;
    }

    public Long getMaxPunctuation() {
        return MaxPunctuation.getValue();
    }

    public void setMaxPunctuation(Long maxPunctuation) {
        if(MaxPunctuation == null) MaxPunctuation = new SimpleLongProperty();
        MaxPunctuation.set(maxPunctuation);
    }

    public IntegerProperty getLifeProperty() {
        return Life;
    }

    public Integer getLife() {
        return Life.getValue();
    }

    public void setLife(Integer life) {
        if(Life == null) Life = new SimpleIntegerProperty();
        Life.set(life);
    }

    public boolean isValueCorrect() {
        return ValueCorrect;
    }

    public void setValueCorrect(boolean valueCorrect) {
        ValueCorrect = valueCorrect;
    }
}
