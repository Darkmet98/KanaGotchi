package Engine.pkgMechanics;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.InsufficientMoney;
import Engine.pkgExceptions.ItemIsZero;
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
    public LongProperty Money;
    public IntegerProperty Status;
    public IntegerProperty Health;
    private LocalDateTime Time;
    private Timer Count;
    private Map<Integer, Integer> ItemsOwned;
    private Items item = new Items();
    private LoadSave load;
    private WriteSave write;
    private Boolean debug = false;

    private static final Logger Log = Logger.getLogger( "DEBUG DATA IN" );

    public Game() {
        load = new LoadSave(this);
        write = new WriteSave(this);
    }

    public Game(long money, int status, int health, LocalDateTime time, Map<Integer, Integer> itemsOwned) {
        setMoney(money);
        setStatus(status);
        setHealth(health);
        setTime(time);
        setItemsOwned(itemsOwned);
    }

    /*
    * Status explanation
    *
    * 0 = angry        Health 1-24
    * 1 = bad          Health 25-44
    * 2 = normal       Health 45-64
    * 3 = happy        Health 65-84
    * 4 = Very happy   Health 85-100
    */

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
        setTime(LocalDateTime.now());
        setItemsOwned(newgame);
        StartTasks();
    }

    public void EnableDebug()  {
        setDebug(true);
    }

    private void StartTasks() {
        //Create the task
        setCount(new Timer());
        TimerTask checkHealth = new TimerTask() {
            @Override
            public void run() {
                DecreaseHealth();
            }
        };
        getCount().schedule(checkHealth, 1000, 1000);
        //getCount().schedule(checkHealth, 60000, 60000);
    }

    //When the character eat food
    public void EatFood(int selected) throws ItemIsZero {
        if(getItemsOwned().get(selected) == 0) throw new ItemIsZero("Actualmente no tienes ese objeto");
        //Se reduce en uno su cantidad
        getItemsOwned().replace(selected, getItemsOwned().get(selected)-1);
        //Se obtiene lo que cura y se cura el personaje
        Items i = (Items) getItem().getItemList().get(selected);
        RecoveryHealth(i.getHealthRecovered());
    }

    //When you buy a item
    public void Buy(int item_selected) throws InsufficientMoney {
        Items i = (Items) getItem().getItemList().get(item_selected);

        //If the price are higher
        if(i.getPrice() > getMoney()) throw new InsufficientMoney("No tienes el dinero suficiente para hacer esta compra");
        setMoney(getMoney() - i.getPrice());
        getItemsOwned().replace(item_selected, getItemsOwned().get(item_selected)+1);
    }

    //Stop the engine
    public void Stop() {
        getCount().cancel();
    }

    public void DecreaseHealth() {
        if (getDebug()) if(getDebug()) Log.log(Level.INFO, "Enabled timer event, decreased one point from "+getHealth().intValue());
        if(getHealth().intValue() != 1)
            Health.set(getHealth().intValue()-1);
            //setHealth(getHealth().intValue()-1);
        ChangeStatus();
    }

    //Change the character status
    public void ChangeStatus() {
        if(getHealth().intValue() < 24) Status.set(0);//setStatus(0);
        else if (getHealth().intValue() > 24 && getHealth().intValue() < 44) Status.set(1);//setStatus(1);
        else if (getHealth().intValue() > 45 && getHealth().intValue() < 64) Status.set(2);//setStatus(2);
        else if (getHealth().intValue() > 65 && getHealth().intValue() < 84) Status.set(3);//setStatus(3);
        else if (getHealth().intValue() > 85) Status.set(4);//setStatus(4);
    }


    public void RecoveryHealth(int recovered) {
        setHealth(getHealth().intValue()+recovered);
        if (getHealth().intValue() > 100) setHealth(100);
    }

    public void save() throws IOException { write.WriteSaveFile(); }

    public void load() throws BadHeaderSave, IOException, SaveFileDoesntExists {
        load.LoadSaveFile();
        StartTasks();
    }

    //Get Set Zone
    private Boolean getDebug() {
        return debug;
    }

    private void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public long getMoney() {
        if(getDebug()) Log.log(Level.INFO, Long.toString(Money.getValue()));
        return Money.getValue();
    }

    public void setMoney(long money) {
        if(getDebug()) Log.log(Level.INFO, Long.toString(money));
        LongProperty result = new SimpleLongProperty();
        result.set(money);
        Money = result;
    }

    public IntegerProperty getStatusInteger() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Status.intValue()));
        return Status;
    }

    public int getStatus() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Status.intValue()));
        return Status.getValue();
    }

    public void setStatus(int status) {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(status));
        IntegerProperty result = new SimpleIntegerProperty();
        result.set(status);
        Status = result;
    }

    public IntegerProperty getHealth() {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(Health.intValue()));
        return Health;
    }

    public void setHealth(int health) {
        if(getDebug()) Log.log(Level.INFO, Integer.toString(health));
        IntegerProperty result = new SimpleIntegerProperty();
        result.set(health);
        Health = result;
    }

    public LocalDateTime getTime() {
        if(getDebug()) Log.log(Level.INFO, Time.toString());
        return Time;
    }

    public void setTime(LocalDateTime time) {
        if(getDebug()) Log.log(Level.INFO, time.toString());
        Time = time;
    }

    private Timer getCount() {
        if(getDebug()) Log.log(Level.INFO, Count.toString());
        return Count;
    }

    private void setCount(Timer count) {
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
}
