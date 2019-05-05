package pkgDebug;

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

    public long readSaveLong(boolean debug) throws IOException {
        long result = readLong();
        if(debug)Log.log(Level.INFO, Long.toString(result));
        return result;
    }

    public LocalDateTime readSaveDate(boolean debug) throws IOException {
        String date = "";
        for(int i = 0; i < 0x1A; i++) date += this.in.read();
        LocalDateTime dateTime = LocalDateTime.parse(date);
        if(debug)Log.log(Level.INFO, date);
        return dateTime;
    }

    public int readSaveInt(boolean debug) throws IOException {
        int result = this.in.read();
        if(debug)Log.log(Level.INFO, Integer.toString(result));
        return result;
    }
}
