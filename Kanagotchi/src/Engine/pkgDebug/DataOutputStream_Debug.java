package Engine.pkgDebug;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataOutputStream_Debug extends DataOutputStream {

    //Logger
    private static final Logger Log = Logger.getLogger( "DEBUG DATA OUT" );

    /*
     * Initialize the class
     */
    public DataOutputStream_Debug(OutputStream out) {
        super(out);
    }

    /*
    * Write a integer value to the local save
     */
    public void writeSave(int b, boolean debug) throws IOException {
        writeInt(b);
        write((int)RandomNumbers());
        if(debug) Log.log(Level.INFO, Integer.toString(b));
    }

    /*
    * Write a long value to the local save
     */
    public void writeSave(long b, boolean debug) throws IOException {
        writeLong(b);
        write((int)RandomNumbers());
        if(debug) Log.log(Level.INFO, Long.toString(b));
    }

    /*
    * Write a string to the local save
     */
    public void writeSave(String b, boolean debug) throws IOException {
        writeUTF(b);
        write((int)RandomNumbers());
        if(debug) Log.log(Level.INFO, b);
    }

    /*
    * Generate a random integer number for security purposes
     */
    private long RandomNumbers() {
        Random random = new Random();
        return random.nextInt(2147483647);
    }

}
