package Engine.pkgGames;

import Engine.pkgMechanics.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Timer;
import java.util.TimerTask;

public class CatchBallMotor {

    //Values
    private IntegerProperty TimeIngame;
    private Timer Count;
    public Common CommonValues;

    /*
     * Initialize the motor
     */
    public CatchBallMotor(Game game) { CommonValues = new Common(game); }

    /*
     * Start the game
     */
    public void StartGame() {
        //Start the game
        CommonValues.StartGame();
        //Set a time to the limit time
        setTimeIngame(20);
        //Load listeners
        LoadListeners();
    }

    /*
     * End the game
     */
    public void EndGame() {
        //If you have a better punctuation
        if (CommonValues.getMaxPunctuation() >  CommonValues.InGameValues.getMaxPunctuationCatchBall())  CommonValues.InGameValues.setMaxPunctuationCatchBall(CommonValues.getMaxPunctuation());
        //Close the engine
        CommonValues.EndGame();
        //Stop the timer count
        Count.cancel();
    }

    /*
    * The ball has catched
     */
    public void BallCatched() {
        //Add one point to the punctuation
        CommonValues.setMaxPunctuation(CommonValues.getMaxPunctuation()+1);
        //Set a new time to the count
        setTimeIngame(20);
    }

    /*
    * Check the time
     */
    private void CheckTimeIngame() {
        setTimeIngame(getTimeIngame()-1);
        //If the time is zero
        if(getTimeIngame()==0) {
            BallNotCached();
            setTimeIngame(20);
        }
    }

    /*
    * The ball doesn't cached
     */
    private void BallNotCached() {
        CommonValues.setLife(CommonValues.getLife()-1);
        if(CommonValues.getLife() == 0) EndGame();
    }

    /*
    * Load a listener
     */
    private void LoadListeners() {
        //Create the task
        Count = new Timer();
        TimerTask checkTimeIngame = new TimerTask() {
            @Override
            public void run() {
                CheckTimeIngame();
            }
        };
        Count.schedule(checkTimeIngame, 1000, 1000);
    }

    //Get Set Zone
    public IntegerProperty getTimeIngameProperty() {
        return TimeIngame;
    }

    public Integer getTimeIngame() {
        return TimeIngame.getValue();
    }

    private void setTimeIngame(Integer timeIngame) {
        if(TimeIngame == null) TimeIngame = new SimpleIntegerProperty();
        TimeIngame.set(timeIngame);
    }
}
