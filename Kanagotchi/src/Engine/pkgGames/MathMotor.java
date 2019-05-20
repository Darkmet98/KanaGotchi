package Engine.pkgGames;

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
    private Random r = new Random();
    private boolean ValueCorrect = false;
    public Common CommonValues;

    public MathMotor(Game game) {
        CommonValues = new Common(game);
    }

    /*
    * Type
    *
    * 0 = Sumar
    * 1 = Restar
    * 2 = Multiplicar
     */


    public void StartGame() {
        CommonValues.StartGame();
        NewCalculation();

    }

    public void CheckResult(Long result) throws OperationResultIsNull {
        if(null == result) throw new OperationResultIsNull("Error, no puedes enviar el resultado en blanco.");
        if(getResult().equals(result)) {
            setValueCorrect(true);
            CommonValues.setMaxPunctuation(CommonValues.getMaxPunctuation()+1);
        }
        else {
            setValueCorrect(false);
            CommonValues.setLife(CommonValues.getLife()-1);
        }

        if(CommonValues.getLife() == 0) {
            CommonValues.EndGame();
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
    public boolean isValueCorrect() {
        return ValueCorrect;
    }

    public void setValueCorrect(boolean valueCorrect) {
        ValueCorrect = valueCorrect;
    }
}
