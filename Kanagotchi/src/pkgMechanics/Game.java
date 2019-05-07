package pkgMechanics;

import pkgExceptions.BadHeaderSave;
import pkgExceptions.InsufficientMoney;
import pkgExceptions.ItemIsZero;
import pkgExceptions.SaveFileDoesntExists;
import pkgSaves.LoadSave;
import pkgSaves.WriteSave;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Game {

    //Values
    private long Money;
    private int Status;
    private int Health;
    private LocalDateTime Time;
    private Timer Count;
    private Map<Integer, Integer> ItemsOwned;
    public Items item = new Items();
    private LoadSave load;
    private WriteSave write;

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
    public void NewGame() {
        Map<Integer, Integer> newgame = new TreeMap<>();
        for(int i = 0; i < item.getItemList().size(); i++) newgame.put(i, 0);
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

    private void StartTasks() {
        //Create the task
        setCount(new Timer());
        TimerTask checkHealth = new TimerTask() {
            @Override
            public void run() {
                DecreaseHealth();
            }
        };
        getCount().schedule(checkHealth, 60000000, 60000000);
    }

    //When the character eat food
    public void EatFood(int selected) throws ItemIsZero {
        if(getItemsOwned().get(selected) == 0) throw new ItemIsZero("Actualmente no tienes ese objeto");
        //Se reduce en uno su cantidad
        getItemsOwned().replace(selected, getItemsOwned().get(selected)-1);
        //Se obtiene lo que cura y se cura el personaje
        Items i = (Items) item.getItemList().get(selected);
        RecoveryHealth(i.getHealthRecovered());
    }

    //When you buy a item
    public void Buy(int item_selected) throws InsufficientMoney {
        Items i = (Items) item.getItemList().get(item_selected);

        //If the price are higher
        if(i.getPrice() > getMoney()) throw new InsufficientMoney("No tienes el dinero suficiente para hacer esta compra");
        setMoney(getMoney() - i.getPrice());
        getItemsOwned().replace(item_selected, getItemsOwned().get(item_selected)+1);
    }


    public void DecreaseHealth() {
        if(getHealth() != 1)
        setHealth(getHealth()-1);
    }

    //Change the character status
    public void ChangeStatus() {
        if(getHealth() < 24) setStatus(0);
        else if (getHealth() > 24 && getHealth() < 44) setStatus(1);
        else if (getHealth() > 45 && getHealth() < 64) setStatus(2);
        else if (getHealth() > 65 && getHealth() < 84) setStatus(3);
        else if (getHealth() > 85) setStatus(4);
    }


    public void RecoveryHealth(int recovered) {
        setHealth(getHealth()+recovered);
        if (getHealth() > 100) setHealth(100);
    }

    public void save() throws IOException { write.WriteSaveFile(); }

    public void load() throws BadHeaderSave, IOException, SaveFileDoesntExists {
        load.LoadSaveFile();
        StartTasks();
    }

    //Get Set Zone
    public long getMoney() { return Money; }
    public void setMoney(long money) { Money = money; }
    public int getStatus() { return Status; }
    public void setStatus(int status) { Status = status; }
    public int getHealth() { return Health; }
    public void setHealth(int health) { Health = health; }
    public LocalDateTime getTime() { return Time; }
    public void setTime(LocalDateTime time) { Time = time; }
    public Timer getCount() { return Count; }
    public void setCount(Timer count) { Count = count; }
    public Map<Integer, Integer> getItemsOwned() { return ItemsOwned; }
    public void setItemsOwned(Map<Integer, Integer> itemsOwned) { ItemsOwned = itemsOwned; }
}
