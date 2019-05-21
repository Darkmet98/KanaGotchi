package Engine.pkgDataBase;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBase_Connection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:49161:xe";

    //  Database credentials
    static final String USER = "Darkmet";
    static final String PASS = "Prueba123*";

    //Database connection
    public static Connection BdConnection = null;


    public void ConnectBD() {
        //Check if the driver exist.
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            return;
        }


        try {
            BdConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            //e.printStackTrace();

        }
    }

}
