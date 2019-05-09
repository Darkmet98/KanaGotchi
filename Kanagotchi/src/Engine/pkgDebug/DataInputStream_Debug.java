package Engine.pkgDebug;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataInputStream_Debug extends DataInputStream {

    private static final Logger Log = Logger.getLogger( "DEBUG DATA IN" );

    public DataInputStream_Debug(InputStream in) {
        super(in);
    }

    public String readSaveMagic(boolean debug) throws IOException {
        String magic = readUTF();
        if(debug)Log.log(Level.INFO, magic);
        skipBytes(1);
        return magic;
    }

    public long readSaveLong(boolean debug) throws IOException {
        long result = readLong();
        if(debug)Log.log(Level.INFO, Long.toString(result));
        skipBytes(1);
        return result;
    }

    public LocalDateTime readSaveDate(boolean debug) throws IOException {
        String date = readUTF();
        LocalDateTime dateTime = LocalDateTime.parse(date);
        if(debug)Log.log(Level.INFO, date);
        skipBytes(1);
        return dateTime;
    }

    public int readSaveInt(boolean debug) throws IOException {
        int result = readInt();
        if(debug)Log.log(Level.INFO, Integer.toString(result));
        skipBytes(1);
        return result;
    }
}
