package Engine.pkgDataBase;

import Engine.pkgExceptions.DBCsvFileDoesntExists;
import Engine.pkgSaves.WriteSave;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBase_Connection {


    //Values
    private File SaveFile = new File(WriteSave.SaveFolder +"/DB.csv");

    // JDBC driver name and database URL
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;

    //Database connection
    public static Connection BdConnection = null;

    /*
    * Read the database credentials
     */
    public void LoadCSV() throws DBCsvFileDoesntExists {
        if(!SaveFile.exists()) throw new DBCsvFileDoesntExists("El csv no existe en la carpeta del juego");
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SaveFile.toURI()),
                StandardCharsets.UTF_8)) {
            // Read the first line from the text file.
            String line = br.readLine();

            /*
             * Use string.split to load a string array with the values from
             * each line of
             * the file, using a comma as the delimiter.
             */
            String[] attributes = line.split(",");

            //Read the driver
            JDBC_DRIVER = attributes[0];

            //Read the database url
            DB_URL = attributes[1];

            //Read the user
            USER = attributes[2];

            //Read the pass
            PASS = attributes[3];

            //Connect to the DB
            ConnectBD();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Connect to the database
     */
    public void ConnectBD() {
        //Check if the driver exist.
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException  e) {
            //Disabled for debug purposes
            //e.printStackTrace();
            return;
        }

        try {
            //  Database credentials
            BdConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            //Disabled for debug purposes
            //e.printStackTrace();

        }
    }

}
