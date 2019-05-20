package Engine.pkgGames;

import Engine.pkgMechanics.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Common {

    private LongProperty MaxPunctuation;
    private IntegerProperty Life;
    public Game InGameValues;

    public Common(Game game) {
        InGameValues = game;
    }

    public void StartGame() {
        setLife(3);
        setMaxPunctuation((long) 0);

    }
    public void EndGame() {
        if (getMaxPunctuation() >  InGameValues.getMaxPunctuationMath())  InGameValues.setMaxPunctuationMath(getMaxPunctuation());
        InGameValues.setMoney( InGameValues.getMoney() + (getMaxPunctuation()*2));
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


}
