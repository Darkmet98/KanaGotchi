package Engine.pkgGames;

import Engine.pkgMechanics.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Common {

    //Values
    private LongProperty MaxPunctuation;
    private IntegerProperty Life;
    public Game InGameValues;

    /*
     * Initialize the motor
     */
    public Common(Game game) { InGameValues = game; }

    /*
    * Start the game
     */
    public void StartGame() {
        //Set the life to 3
        setLife(3);
        //Set the max punctuation to zero
        setMaxPunctuation((long) 0);

    }


    /*
    * End the current game
     */
    public void EndGame() {
        //Set the new money
        InGameValues.setMoney( InGameValues.getMoney() + (getMaxPunctuation()*2));
        //Set the new experience acquired
        InGameValues.setExperience((int) (InGameValues.getExperience()+getMaxPunctuation()));
    }

    //Get set zone
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
