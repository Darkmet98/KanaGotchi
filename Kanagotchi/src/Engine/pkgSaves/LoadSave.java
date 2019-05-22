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

    //Values
    private Game Gameor;
    private WriteSave write = new WriteSave();

    /*
    * Initialize the class
     */
    public LoadSave(Game game) { Gameor = game; }

    /*
    * Load the local save file
     */
    public void LoadSaveFile() throws SaveFileDoesntExists, IOException, BadHeaderSave {
        //Check if the file exists.
        if(!write.getSaveFile().exists()) throw new SaveFileDoesntExists("La partida no existe.");
        //Load the save
        FileInputStream load = new FileInputStream(write.getSaveFile());
        DataInputStream_Debug load_stream = new DataInputStream_Debug(load);
        //Check the magic
        if(!load_stream.readSaveMagic(Gameor.getDebug()).equals("KNTI")) throw new BadHeaderSave("La partida está corrupta");
        //Read the money
        Gameor.setMoney(write.Encrypt(load_stream.readSaveLong(Gameor.getDebug())));
        //Read the status
        Gameor.setStatus((int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        //Read the Health
        Gameor.setHealth((int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        //Read the Experience
        Gameor.setExperience((int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        //Read the level
        Gameor.setPlayerLevel((int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        //Read the Character selected
        Gameor.setCharacterSelected((int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        //Read the Math Game Max punctuation
        Gameor.setMaxPunctuationMath(write.Encrypt(load_stream.readSaveLong(Gameor.getDebug())));
        //Read the Catch the Ball Game Max punctuation
        Gameor.setMaxPunctuationCatchBall(write.Encrypt(load_stream.readSaveLong(Gameor.getDebug())));
        //Read the Time
        Gameor.setTime(load_stream.readSaveDate(Gameor.getDebug()));
        //Read the items owned
        Map<Integer, Integer> temp = new TreeMap<>();
        for(int i = 0; i < Gameor.getItem().getItemList().size(); i++) temp.put(i, (int)write.Encrypt(load_stream.readSaveInt(Gameor.getDebug())));
        Gameor.setItemsOwned(temp);
        load_stream.close();
        load.close();
    }

}
