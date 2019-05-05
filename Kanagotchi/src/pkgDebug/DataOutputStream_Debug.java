package pkgDebug;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataOutputStream_Debug extends DataOutputStream {

    private static final Logger Log = Logger.getLogger( "DEBUG DATA OUT" );


    public DataOutputStream_Debug(OutputStream out) {
        super(out);
    }

    public void writeSave(int b, boolean debug) throws IOException {
        write(b);
        write((int)RandomNumbers());
        if(debug) Log.log(Level.INFO, Integer.toString(b));
    }

    public void writeSave(long b, boolean debug) throws IOException {
        writeLong(b);
        write((int)RandomNumbers());
        if(debug) Log.log(Level.INFO, Long.toString(b));
    }

    public void writeSave(byte[] b, boolean debug) throws IOException {
        write(b);
        write((int)RandomNumbers());
        if(debug) {
            String logstring = new String(b);
            Log.log(Level.INFO, logstring);
        }
    }

    private long RandomNumbers() {
        Random random = new Random();
        return random.nextInt(2147483647);
    }

}
