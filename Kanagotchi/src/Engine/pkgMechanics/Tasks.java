package Engine.pkgMechanics;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class Tasks {

    private Game GameValues;

    public Tasks(Game game) {
        GameValues = game;
    }

    public void StartTasks() {
        //Create the task
        GameValues.setCount(new Timer());
        TimerTask checkHealth = new TimerTask() {
            @Override
            public void run() {
                DecreaseHealth();
            }
        };
        GameValues.getCount().schedule(checkHealth, 60000, 60000);
    }

    public void DecreaseHealth() {
        if (GameValues.getDebug()) Game.Log.log(Level.INFO, "Enabled timer event, decreased one point from "+GameValues.getHealth());
        if(GameValues.getHealth() != 1) GameValues.setHealth(GameValues.getHealth()-1);
        ChangeStatus();
    }

    //Change the character status
    public void ChangeStatus() {
        if(GameValues.getHealth() < 24) GameValues.setStatus(0);
        else if (GameValues.getHealth() > 24 && GameValues.getHealth() < 44) GameValues.setStatus(1);
        else if (GameValues.getHealth() > 45 && GameValues.getHealth() < 64) GameValues.setStatus(2);
        else if (GameValues.getHealth() > 65 && GameValues.getHealth() < 84) GameValues.setStatus(3);
        else if (GameValues.getHealth() > 85) GameValues.setStatus(4);
    }
}
