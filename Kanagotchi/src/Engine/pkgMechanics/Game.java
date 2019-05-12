package Engine.pkgMechanics;

import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgExceptions.ItemIsZero;
import Engine.pkgItems.Items;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.SaveFileDoesntExists;
import Engine.pkgSaves.LoadSave;
import Engine.pkgSaves.WriteSave;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    //Values
    private LongProperty Money;
    private IntegerProperty Status;
    private IntegerProperty Health;
    private IntegerProperty PlayerLevel;
    private IntegerProperty Experience;
    private LocalDateTime Time;
    private Timer Count;
    private Map<Integer, Integer> ItemsOwned;
    private Items item = new Items();
    private LoadSave load;
    private WriteSave write;
    private Boolean debug = false;
    private Tasks Task;
    private Character Chara;
    private Shop ShopOperations;
    private boolean EngineStarted = false;

    public static final Logger Log = Logger.getLogger( "DEBUG DATA IN" );

    public Game() {
        load = new LoadSave(this);
        write = new WriteSave(this);
        Task = new Tasks(this);
        Chara = new Character(this);
        ShopOperations = new Shop(this);
    }

    /*public Game(long money, int status, int health, LocalDateTime time, Map<Integer, Integer> itemsOwned) {
        setMoney(money);
        setStatus(status);
        setHealth(health);
        setTime(time);
        setItemsOwned(itemsOwned);
    }*/

    //When you initialize the first time the game.
    public void NewGame(boolean debug) {
        if(debug) EnableDebug();
        Map<Integer, Integer> newgame = new TreeMap<>();
        for(int i = 0; i < getItem().getItemList().size(); i++) newgame.put(i, 0);
        newgame.replace(0,2);
        newgame.replace(1,2);
        //Set the initial values
        setMoney(100);
        setStatus(4);
        setHealth(100);
        setPlayerLevel(1);
        setExperience(1);
        setTime(LocalDateTime.now());
        setItemsOwned(newgame);
        Task.StartTasks();
        setEngineStarted(true);
    }

    private void EnableDebug()  {
        setDebug(true);
    }
    public void save() throws IOException { write.WriteSaveFile(); }
    public void load() throws BadHeaderSave, IOException, SaveFileDoesntExists {
        load.LoadSaveFile();
        Task.StartTasks();
        setEngineStarted(true);
    }
    //Stop the engine
    public void Stop() { getCount().cancel(); }

    public void EatFood(int selected) throws ItemIsZero { Chara.EatFood(selected); }
    public void Buy(int item_selected) throws InsufficientMoney {ShopOperations.Buy(item_selected);}

    //For tests
    public void DecreaseHealth() {Task.DecreaseHealth();}
    public void ChangeStatus() {Task.ChangeStatus();}
    public void RecoveryHealth(int recovered) {Chara.RecoveryHealth(recovered);}

    //Get Set Zone
    public Boolean getDebug() {
        return debug;
    }

    private void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public long getMoney() {
        if(getDebug()) Log.log(Level.INFO, Long.toString(Money.getValue()));
        return Money.getValue();
    }

    public LongProperty getMoneyProperty() {
        if(getDebug()) Log.log(Level.INFO, Long.toString(Money.getValue()));
        return Money;
    }

    public void setMoney(long money) {
        if(Money == null) Money = new SimpleLongProperty();
        if(getDebug()) Log.log(Level.INFO, Long.toString(money));
        Money.set(money);
    }

    public int getStatus() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Status.intValue()));
        return Status.getValue();
    }

    public IntegerProperty getStatusProperty() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Status.intValue()));
        return Status;
    }

    public void setStatus(int status) {
        if(Status == null) Status = new SimpleIntegerProperty();
        if(getDebug()) Log.log(Level.INFO, Integer.toString(status));
        Status.set(status);
    }

    public int getHealth() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Health.intValue()));
        return Health.getValue();
    }

    public IntegerProperty getHealthProperty() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Health.intValue()));
        return Health;
    }

    public void setHealth(int health) {
        if(Health == null) Health = new SimpleIntegerProperty();
        if(getDebug()) Log.log(Level.INFO, Integer.toString(health));
        Health.set(health);
    }

    public LocalDateTime getTime() {
        if(getDebug()) Log.log(Level.INFO, Time.toString());
        return Time;
    }

    public void setTime(LocalDateTime time) {
        if(getDebug()) Log.log(Level.INFO, time.toString());
        Time = time;
    }

    public Timer getCount() {
        if(getDebug()) Log.log(Level.INFO, Count.toString());
        return Count;
    }

    public void setCount(Timer count) {
        if(getDebug()) Log.log(Level.INFO, count.toString());
        Count = count;
    }

    public Map<Integer, Integer> getItemsOwned() {
        for(int i = 0; i < getItem().getItemList().size(); i++) if(getDebug()) Log.log(Level.INFO, Integer.toString(ItemsOwned.get(i)));
        return ItemsOwned;
    }

    public void setItemsOwned(Map<Integer, Integer> itemsOwned) {
        for(int i = 0; i < getItem().getItemList().size(); i++) if(getDebug()) Log.log(Level.INFO, Integer.toString(itemsOwned.get(i)));
        ItemsOwned = itemsOwned;
    }

    public Items getItem() {
        return item;
    }

    public int getPlayerLevel() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(PlayerLevel.intValue()));
        return PlayerLevel.getValue();
    }

    public IntegerProperty getPlayerLevelProperty() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(PlayerLevel.getValue()));
        return PlayerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        if(PlayerLevel == null) PlayerLevel = new SimpleIntegerProperty();
        if(getDebug()) Log.log(Level.INFO, Integer.toString(PlayerLevel.getValue()));
        PlayerLevel.set(playerLevel);
    }

    public int getExperience() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Experience.getValue()));
        return Experience.getValue();
    }

    public IntegerProperty getExperienceProperty() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Experience.getValue()));
        return Experience;
    }

    public void setExperience(int experience) {
        if(Experience == null) Experience = new SimpleIntegerProperty();
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Experience.getValue()));
        Experience.set(experience);
    }

    public boolean isEngineStarted() {
        return EngineStarted;
    }

    public void setEngineStarted(boolean engineStarted) {
        EngineStarted = engineStarted;
    }
}
