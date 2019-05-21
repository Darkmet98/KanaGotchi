package Engine.pkgMechanics;

import Engine.pkgDataBase.DataBase_Connection;
import Engine.pkgDataBase.DataBase_Tables;
import Engine.pkgExceptions.*;
import Engine.pkgGames.CatchBallMotor;
import Engine.pkgGames.MathMotor;
import Engine.pkgItems.Items;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import Engine.pkgSaves.LoadSave;
import Engine.pkgSaves.WriteSave;


import java.io.IOException;
import java.sql.SQLException;
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
    private int CharacterSelected;
    private Timer Count;
    private Map<Integer, Integer> ItemsOwned;

    //MiniGames
    private LongProperty MaxPunctuationMath;
    private LongProperty MaxPunctuationCatchBall;

    //Methods
    private Items item = new Items();
    private LoadSave load;
    private WriteSave write;
    private DataBase_Connection DbConnect;
    public DataBase_Tables DbTables;
    private Boolean debug = false;
    private Tasks Task;
    private Character Chara;
    public MathMotor Maths;
    public CatchBallMotor CatchBall;
    private Shop ShopOperations;
    private boolean EngineStarted = false;
    private boolean BdFailed = false;

    public static final Logger Log = Logger.getLogger( "DEBUG DATA IN" );

    public Game() {
        load = new LoadSave(this);
        write = new WriteSave(this);
        Task = new Tasks(this);
        Chara = new Character(this);
        ShopOperations = new Shop(this);
        Maths = new MathMotor(this);
        CatchBall = new CatchBallMotor(this);
        DbTables = new DataBase_Tables(this);
        DbConnect = new DataBase_Connection();
    }

    //When you initialize the first time the game.
    public void NewGame(boolean debug) {
        //Check the BD
        try{
            DbConnect.ConnectBD();
            DbTables.CheckTable();
        }
        catch (Exception e) {}


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
        setMaxPunctuationMath(0L);
        setMaxPunctuationCatchBall(0L);
        setTime(LocalDateTime.now());
        setItemsOwned(newgame);
        Task.StartTasks();
        setEngineStarted(true);
    }

    private void EnableDebug()  {
        setDebug(true);
    }
    public void save() throws IOException {
        try {
            DbConnect.ConnectBD();
            DbTables.WriteSaveToBD();
        }
        catch (Exception e) {
            setBdFailed(true);
        }
        write.WriteSaveFile();
         }
    public void load() throws BadHeaderSave, IOException, SaveFileDoesntExists {

        try {
            DbConnect.ConnectBD();
            DbTables.LoadSaveBD();
        }
        catch (Exception e) {
            e.printStackTrace();
            setBdFailed(true);
        }

        //If the DB Failed to load
        if(isBdFailed()) {
            load.LoadSaveFile();
        }

        Task.StartTasks();
        setEngineStarted(true);
    }
    //Stop the engine
    public void Stop() { getCount().cancel(); }

    public void EatFood(int selected) throws ItemIsZero, ItemNotSelected { Chara.EatFood(selected); }
    public void Buy(int item_selected) throws InsufficientMoney, ItemNotSelected {ShopOperations.Buy(item_selected);}

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

    public int getCharacterSelected() {
        return CharacterSelected;
    }

    public void setCharacterSelected(int characterSelected) {
        CharacterSelected = characterSelected;
    }

    public LongProperty getMaxPunctuationMathProperty() {
        if(getDebug()) Log.log(Level.INFO, Long.toString(getMaxPunctuationMath()));
        return MaxPunctuationMath;
    }
    public Long getMaxPunctuationMath() {
        return MaxPunctuationMath.getValue();
    }

    public void setMaxPunctuationMath(Long maxPunctuationMath) {
        if(MaxPunctuationMath == null) MaxPunctuationMath = new SimpleLongProperty();
        MaxPunctuationMath.set(maxPunctuationMath);
    }

    public LongProperty getMaxPunctuationCatchBallProperty() {
        return MaxPunctuationCatchBall;
    }

    public Long getMaxPunctuationCatchBall() {
        return MaxPunctuationCatchBall.getValue();
    }

    public void setMaxPunctuationCatchBall(Long maxPunctuationCatchBall) {
        if(MaxPunctuationCatchBall == null) MaxPunctuationCatchBall = new SimpleLongProperty();
        MaxPunctuationCatchBall.set(maxPunctuationCatchBall);
    }

    public boolean isBdFailed() {
        return BdFailed;
    }

    public void setBdFailed(boolean bdFailed) {
        BdFailed = bdFailed;
    }
}
