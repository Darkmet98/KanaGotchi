package Engine.pkgSaves;

import Engine.pkgDebug.DataInputStream_Debug;
import Engine.pkgExceptions.BadHeaderSave;
import Engine.pkgExceptions.SaveFileDoesntExists;
import Engine.pkgMechanics.Game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LoadSave {

    public LoadSave(Game game) { Gameor = game; }
    private Game Gameor;
    private DataInputStream_Debug load_stream;
    private WriteSave write = new WriteSave();

    public void LoadSaveFile() throws SaveFileDoesntExists, IOException, BadHeaderSave {
        //Check if the file exists.
        if(!write.getSaveFile().exists()) throw new SaveFileDoesntExists("La partida no existe.");
        //Load the save
        FileInputStream load = new FileInputStream(write.getSaveFile());
        load_stream = new DataInputStream_Debug(load);
        //Check the magic
        if(!load_stream.readSaveMagic(true).equals("KNTI")) throw new BadHeaderSave("La partida está corrupta");
        //Read the money
        Gameor.setMoney(write.Encrypt(load_stream.readSaveLong(true)));
        //Read the status
        Gameor.setStatus((int)write.Encrypt(load_stream.readSaveInt(true)));
        //Read the Health
        Gameor.setHealth((int)write.Encrypt(load_stream.readSaveInt(true)));
        //Read the Time
        Gameor.setTime(load_stream.readSaveDate(true));
        //Read the items owned
        Map<Integer, Integer> temp = new TreeMap<>();
        for(int i = 0; i < Gameor.getItem().getItemList().size(); i++) temp.put(i, (int)write.Encrypt(load_stream.readSaveInt(true)));
        Gameor.setItemsOwned(temp);
        load_stream.close();
        load.close();
    }

}
