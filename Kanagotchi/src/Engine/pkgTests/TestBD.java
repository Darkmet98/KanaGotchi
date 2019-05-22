package Engine.pkgTests;

import Engine.pkgDataBase.DataBase_Connection;
import Engine.pkgMechanics.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBD {

    private static Game testingame;

    @BeforeAll
    static void Initialize() {
        testingame= new Game();
    }

    @Test
    @DisplayName("Test BD.")
    public void TestTheBD() throws SQLException {
        DataBase_Connection bd = new DataBase_Connection();
        bd.ConnectBD();
        testingame.DbTables.CheckTable();
        //testingame.NewGame(false);
        //testingame.DbTables.WriteSaveToBD();
        testingame.DbTables.LoadSaveBD();
        assertNotNull( DataBase_Connection.BdConnection);
    }

}
