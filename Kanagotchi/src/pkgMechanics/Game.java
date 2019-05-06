package pkgMechanics;

import pkgDebug.DataInputStream_Debug;
import pkgDebug.DataOutputStream_Debug;
import pkgExceptions.BadHeaderSave;
import pkgExceptions.InsufficientMoney;
import pkgExceptions.ItemIsZero;
import pkgExceptions.SaveFileDoesntExists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private File SaveFolder = new File(System.getProperty("user.home")+"/KanaGotchi");
    private File SaveFile = new File(getSaveFolder() +"/save.sav");
    private DataOutputStream_Debug save_stream;
    private DataInputStream_Debug load_stream;

    public Game() {}

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
        StartGame(100, 4, 100, LocalDateTime.now(), newgame);
    }

    public void StartGame(long save_money, int save_status, int save_health, LocalDateTime save_time, Map<Integer, Integer> save_itemsOwned) {

        //Load the save
        setMoney(save_money);
        setStatus(save_status);
        setHealth(save_health);
        setTime(save_time);
        setItemsOwned(save_itemsOwned);
        setCount(new Timer());

        //Create the task
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

    public void save() throws IOException {
        if(!getSaveFolder().exists()) getSaveFolder().mkdirs();
        FileOutputStream save = new FileOutputStream(getSaveFile());
        save_stream = new DataOutputStream_Debug(save);
        //MAGIC
        save_stream.writeSave("KNTI", true);
        //Money
        //save_stream.writeSave((Encrypt(getMoney())), true);
        save_stream.writeSave((Encrypt(getMoney())), true);
        //Status
        save_stream.writeSave(((int)Encrypt(getStatus())), true);
        //Health
        save_stream.writeSave((int)Encrypt(getHealth()), true);
        //Time
        save_stream.writeSave(getTime().toString(), true);
        //ItemsOwned
        for(int i = 0; i < getItemsOwned().size(); i++) {
            //Write the item
            save_stream.writeSave(((int)Encrypt(getItemsOwned().get(i))), true);
        }
        save_stream.flush();
        save_stream.close();
        save.close();
    }

    public void load() throws BadHeaderSave, IOException, SaveFileDoesntExists {
        //Check if the file exists.
        if(!getSaveFile().exists()) throw new SaveFileDoesntExists("La partida no existe.");
        //Load the save
        FileInputStream load = new FileInputStream(getSaveFile());
        load_stream = new DataInputStream_Debug(load);
        //Check the magic
        if(!load_stream.readSaveMagic(true).equals("KNTI")) throw new BadHeaderSave("La partida está corrupta");
        //Read the money
        setMoney(Encrypt(load_stream.readSaveLong(true)));
        //Read the status
        setStatus((int)Encrypt(load_stream.readSaveInt(true)));
        //Read the Health
        setHealth((int)Encrypt(load_stream.readSaveInt(true)));
        //Read the Time
        setTime(load_stream.readSaveDate(true));
        //Read the items owned
        Map<Integer, Integer> temp = new TreeMap<>();
        for(int i = 0; i < item.getItemList().size(); i++) temp.put(i, (int)Encrypt(load_stream.readSaveInt(true)));
        setItemsOwned(temp);
    }

    private long Encrypt(long value) { return value^456970; }


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

    public File getSaveFolder() {
        return SaveFolder;
    }

    public File getSaveFile() {
        return SaveFile;
    }
}
