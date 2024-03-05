package com.tempSensor.backend.database.debug;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DebugController {

    static String url = "jdbc:sqlite:C:/Users/Nutzer/Documents/GitHub/backendTempSensor/src/main/java/com/tempSensor/backend/database/database.db";

    public static void creatDataBase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void createTable(String tableName, String sql) {
        if (sql == null) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                    + " date text,\n"
                    + " time text,\n"
                    + "	temp real NOT NULL,\n"
                    + "	humid real NOT NULL\n"
                    + ");";
        }

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getNumberOfentrys() {

        String sql = "SELECT * FROM data";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            int index = 0;

            do {
                index++;
            } while (rs.next());

            return index;
        } catch (SQLException e) {
            throw new RuntimeException(e.toString());
        }
    }

}