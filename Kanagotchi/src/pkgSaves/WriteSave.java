package pkgSaves;

import pkgDebug.DataOutputStream_Debug;
import pkgMechanics.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteSave {

    public WriteSave() {}
    public WriteSave(Game game) { Gameor = game; }

    private File SaveFolder = new File(System.getProperty("user.home")+"/KanaGotchi");
    private File SaveFile = new File(getSaveFolder() +"/save.sav");

    private DataOutputStream_Debug save_stream;
    private Game Gameor;

    public void WriteSaveFile() throws IOException {
        if(!getSaveFolder().exists()) getSaveFolder().mkdirs();
        FileOutputStream save = new FileOutputStream(getSaveFile());
        save_stream = new DataOutputStream_Debug(save);
        //MAGIC
        save_stream.writeSave("KNTI", true);
        //Money
        save_stream.writeSave((Encrypt(Gameor.getMoney())), true);
        //Status
        save_stream.writeSave(((int)Encrypt(Gameor.getStatus())), true);
        //Health
        save_stream.writeSave((int)Encrypt(Gameor.getHealth()), true);
        //Time
        save_stream.writeSave(Gameor.getTime().toString(), true);
        //ItemsOwned
        for(int i = 0; i < Gameor.getItemsOwned().size(); i++) { save_stream.writeSave(((int)Encrypt(Gameor.getItemsOwned().get(i))), true); }
        save_stream.flush();
        save_stream.close();
        save.close();
    }

    public long Encrypt(long value) { return value^456970; }
    //Get Set Zone
    public File getSaveFolder() { return SaveFolder; }
    public File getSaveFile() { return SaveFile; }
}
