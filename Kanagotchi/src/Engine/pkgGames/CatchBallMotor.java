package Engine.pkgGames;

import Engine.pkgMechanics.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Timer;
import java.util.TimerTask;

public class CatchBallMotor {

    private IntegerProperty TimeIngame;
    private Timer Count;
    public Common CommonValues;

    public CatchBallMotor(Game game) {
        CommonValues = new Common(game);
    }


    public void StartGame() {
        CommonValues.StartGame();
        setTimeIngame(20);
        LoadListeners();
    }


    public void EndGame() {
        if (CommonValues.getMaxPunctuation() >  CommonValues.InGameValues.getMaxPunctuationCatchBall())  CommonValues.InGameValues.setMaxPunctuationCatchBall(CommonValues.getMaxPunctuation());
        CommonValues.EndGame();
        Count.cancel();
    }

    public void BallCatched() {
        CommonValues.setMaxPunctuation(CommonValues.getMaxPunctuation()+1);
        setTimeIngame(20);
    }

    private void CheckTimeIngame() {
        setTimeIngame(getTimeIngame()-1);
        if(getTimeIngame()==0) {
            BallNotCatched();
            setTimeIngame(20);
        }
    }

    private void BallNotCatched() {
        CommonValues.setLife(CommonValues.getLife()-1);
        if(CommonValues.getLife() == 0) EndGame();
    }

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

    public void setTimeIngame(Integer timeIngame) {
        if(TimeIngame == null) TimeIngame = new SimpleIntegerProperty();
        TimeIngame.set(timeIngame);
    }
}
