package Engine.pkgSaves;

import Engine.pkgDebug.DataOutputStream_Debug;
import Engine.pkgMechanics.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteSave {

    public WriteSave() {}
    public WriteSave(Game game) { Gameor = game; }

    private static File SaveFolder = new File(System.getProperty("user.home")+"/KanaGotchi");
    public static File SaveFile = new File(SaveFolder +"/save.sav");

    private DataOutputStream_Debug save_stream;
    private Game Gameor;

    public void WriteSaveFile() throws IOException {
        if(!getSaveFolder().exists()) getSaveFolder().mkdirs();
        FileOutputStream save = new FileOutputStream(getSaveFile());
        save_stream = new DataOutputStream_Debug(save);
        //MAGIC
        save_stream.writeSave("KNTI", Gameor.getDebug());
        //Money
        save_stream.writeSave((Encrypt(Gameor.getMoney())), Gameor.getDebug());
        //Status
        save_stream.writeSave(((int)Encrypt(Gameor.getStatus())), Gameor.getDebug());
        //Health
        save_stream.writeSave((int)Encrypt(Gameor.getHealth()), Gameor.getDebug());
        //Experience
        save_stream.writeSave((int)Encrypt(Gameor.getExperience()), Gameor.getDebug());
        //Level
        save_stream.writeSave((int)Encrypt(Gameor.getPlayerLevel()), Gameor.getDebug());
        //Character
        save_stream.writeSave((int)Encrypt(Gameor.getCharacterSelected()), Gameor.getDebug());
        //Time
        save_stream.writeSave(Gameor.getTime().toString(), Gameor.getDebug());
        //ItemsOwned
        for(int i = 0; i < Gameor.getItemsOwned().size(); i++) { save_stream.writeSave(((int)Encrypt(Gameor.getItemsOwned().get(i))), Gameor.getDebug()); }
        save_stream.flush();
        save_stream.close();
        save.close();
    }

    public long Encrypt(long value) { return value^456970; }
    //Get Set Zone
    public File getSaveFolder() { return SaveFolder; }
    public File getSaveFile() { return SaveFile; }
}
