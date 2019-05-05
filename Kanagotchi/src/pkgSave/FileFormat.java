package pkgSave;

import pkgDebug.DataInputStream_Debug;
import pkgDebug.DataOutputStream_Debug;
import pkgExceptions.BadHeaderSave;
import pkgExceptions.SaveFileDoesntExists;
import pkgMechanics.Game;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Map;

public class FileFormat extends Game {

    private File SaveFolder = new File(System.getProperty("user.home")+"/KanaGotchi");
    private File SaveFile = new File(getSaveFolder() +"/save.sav");
    private DataOutputStream_Debug save_stream;
    private DataInputStream_Debug load_stream;

    public FileFormat(long money, int status, int health, LocalDateTime time, Map<Integer, Integer> itemsOwned) {
        super(money, status, health, time, itemsOwned);
    }

    public void SaveFile() throws IOException {
        if(!getSaveFolder().exists()) getSaveFolder().mkdirs();
        FileOutputStream save = new FileOutputStream(getSaveFile());
        save_stream = new DataOutputStream_Debug(save);

        //MAGIC
        save_stream.writeSave("KNTI".getBytes(), true);

        //Money
        save_stream.writeSave((Encrypt(FileFormat.super.getMoney())), true);

        //Status
        save_stream.writeSave(((int)Encrypt(FileFormat.super.getStatus())), true);

        //Health
        save_stream.writeSave((int)Encrypt(FileFormat.super.getHealth()), true);

        //Time
        save_stream.writeSave(FileFormat.super.getTime().toString().getBytes(), true);

        //ItemsOwned
        for(int i = 0; i < FileFormat.super.getItemsOwned().size(); i++) {
            //Write the item
            save_stream.writeSave(((int)Encrypt(FileFormat.super.getItemsOwned().get(i))), true);
        }

        save_stream.flush();
        save_stream.close();
        save.close();
    }

    public void LoadFile() throws IOException, SaveFileDoesntExists, BadHeaderSave {
        //Check if the file exists.
        if(!SaveFile.exists()) throw new SaveFileDoesntExists("La partida no existe.");
        //Load the save
        FileInputStream load = new FileInputStream(getSaveFile());
        load_stream = new DataInputStream_Debug(load);
        //Check the magic
        if(!load_stream.readNBytes(4).toString().equals("KNTI")) throw new BadHeaderSave("La partida está corrupta");
        //Skip the padding
        load_stream.skipBytes(1);
        //Read the money
        long money = load_stream.readLong();
        FileFormat.super.setMoney(money);
        System.out.println("DEBUG: SAVE"+money); //DEBUG
        //Skip the padding
        load_stream.skipBytes(1);


    }

    private long Encrypt(long value) { return value^456970; }

    //Get Set Zone
    public File getSaveFolder() {
        return SaveFolder;
    }

    public File getSaveFile() {
        return SaveFile;
    }
}
