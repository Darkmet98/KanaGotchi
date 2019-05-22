package Engine.pkgDataBase;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBase_Connection {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:49161:xe";

    //Database connection
    public static Connection BdConnection = null;


    /*
    * Connect to the database
     */
    public void ConnectBD() {
        //Check if the driver exist.
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            //Disabled for debug purposes
            //e.printStackTrace();
            return;
        }

        try {
            //  Database credentials
            String USER = "Darkmet";
            String PASS = "Prueba123*";
            BdConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            //Disabled for debug purposes
            //e.printStackTrace();

        }
    }

}
