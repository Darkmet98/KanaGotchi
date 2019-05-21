package Engine.pkgDataBase;

import Engine.pkgMechanics.Game;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class DataBase_Tables  {

    private Statement LineSql;
    private ResultSet ResultadoSql;
    private Game GameValues;

    public DataBase_Tables(Game game) {
        GameValues = game;

    }

    public void CheckTable() throws SQLException {
        LineSql = DataBase_Connection.BdConnection.createStatement();
        ResultadoSql = LineSql.executeQuery("select count(*)\n" +
                "from all_objects\n" +
                "where object_type in ('TABLE','VIEW')\n" +
                "and object_name = UPPER('KanaGotchi_Save')");
        if(ResultadoSql.next()) {
            //If the table doesn't exists
            if(ResultadoSql.getByte(1) == 0) {
                CreateTable();
            }
        }
    }

    private void CreateTable() throws SQLException {
        LineSql = DataBase_Connection.BdConnection.createStatement();
        //Create the table
        ResultadoSql = LineSql.executeQuery("create table KanaGotchi_Save(\n" +
                "    magic varchar2(4) constraint KGS_magic_NN not null,\n" +
                "    money long constraint KGS_money_NN not null,\n" +
                "    status integer constraint KGS_status_NN not null,\n" +
                "    health integer constraint KGS_health_NN not null,\n" +
                "    experience integer constraint KGS_experience_NN not null,\n" +
                "    levelGame integer constraint KGS_levelGame_NN not null,\n" +
                "    character integer constraint KGS_character_NN not null,\n" +
                "    MGame integer constraint KGS_Mgame_NN not null,\n" +
                "    CTBGame integer constraint KGS_CTBGame_NN not null,\n" +
                "    gameDate varchar2(26) constraint KGS_gameDate_NN not null\n" +
                ")");
        //Add the first line of data
        ResultadoSql = LineSql.executeQuery("Insert into KanaGotchi_Save (magic, money, status, health, experience, levelGame, character, MGame, CTBGame, gameDate)" +
                " values ('KNTI', 0, 0, 0, 0, 0, 0, 0, 0, 'Sample')");

        //Add the items
        for(int i = 0; i < GameValues.getItem().getItemList().size(); i++) {
            ResultadoSql = LineSql.executeQuery("ALTER TABLE KanaGotchi_Save\n" +
                    "  ADD item" + i +" integer DEFAULT 0");
        }
    }

    public void WriteSaveToBD() throws SQLException {
        LineSql = DataBase_Connection.BdConnection.createStatement();
        //Write the actual stats
        ResultadoSql = LineSql.executeQuery(
                "update KanaGotchi_Save\n" +
                "    set money = "+ GameValues.getMoney() + ",\n" +
                "    status = "+ GameValues.getStatus() + ",\n" +
                "    health = "+ GameValues.getHealth() + ",\n" +
                "    experience = "+ GameValues.getExperience() + ",\n" +
                "    levelGame = "+ GameValues.getPlayerLevel() + ",\n" +
                "    character = "+ GameValues.getCharacterSelected() + ",\n" +
                "    MGame = " +  GameValues.getMaxPunctuationMath() + ",\n" +
                "    CTBGame = "+ GameValues.getMaxPunctuationCatchBall() + ",\n" +
                "    gameDate = "+ "'" + GameValues.getTime().toString() + "' \n" +
                "    where magic = 'KNTI'");


        //Write the items obtained
        for(int i = 0; i < GameValues.getItemsOwned().size(); i++) {
            ResultadoSql = LineSql.executeQuery("update KanaGotchi_Save " +
                    "set ITEM" + i + " = " + GameValues.getItemsOwned().get(i) + " \n" +
                    "where magic = 'KNTI'");
        }

    }


    public void LoadSaveBD() throws SQLException {
        LineSql = DataBase_Connection.BdConnection.createStatement();
        //Load the Money
        ResultadoSql = LineSql.executeQuery("select money from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setMoney(ResultadoSql.getLong(1));

        //Load the Status
        ResultadoSql = LineSql.executeQuery("select status from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setStatus(ResultadoSql.getInt(1));

        //Load the Health
        ResultadoSql = LineSql.executeQuery("select health from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setHealth(ResultadoSql.getInt(1));

        //Load the Experience
        ResultadoSql = LineSql.executeQuery("select experience from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setExperience(ResultadoSql.getInt(1));

        //Load the Level
        ResultadoSql = LineSql.executeQuery("select levelGame from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setPlayerLevel(ResultadoSql.getInt(1));

        //Load the Character
        ResultadoSql = LineSql.executeQuery("select character from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setCharacterSelected(ResultadoSql.getInt(1));

        //Load the Math Game Max punctuation
        ResultadoSql = LineSql.executeQuery("select MGame from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setMaxPunctuationMath(ResultadoSql.getLong(1));

        //Load the Catch The Ball Game Max punctuation
        ResultadoSql = LineSql.executeQuery("select CTBGame from KanaGotchi_Save");
        if(ResultadoSql.next()) GameValues.setMaxPunctuationCatchBall(ResultadoSql.getLong(1));

        //Load the Date
        ResultadoSql = LineSql.executeQuery("select gameDate from KanaGotchi_Save");
        String date = "";
        if(ResultadoSql.next()) date = ResultadoSql.getString(1);
        LocalDateTime dateTime = LocalDateTime.parse(date);
        GameValues.setTime(dateTime);

        //Load the items
        Map<Integer, Integer> temp = new TreeMap<>();
        for(int i = 0; i < GameValues.getItem().getItemList().size(); i++) {
            ResultadoSql = LineSql.executeQuery("select ITEM" + i + " from KanaGotchi_Save");
            if(ResultadoSql.next()) temp.put(i, ResultadoSql.getInt(1));
        }
        GameValues.setItemsOwned(temp);
    }

}
