package com.tempSensor.backend.database.functions;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tempSensor.backend.database.models.dbType;
import com.tempSensor.backend.sensor.models.sensorData;

public class Functions {

    static String url = "jdbc:sqlite:C:/Users/Nutzer/Documents/GitHub/backendTempSensor/src/main/java/com/tempSensor/backend/database/database.db";

    public static void writeToDatabase(sensorData data) {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String sql = "INSERT INTO data (date, time, temp, humid) VALUES ('" + date + "', '" + time
                + "', '" + data.temp() + "', '" + data.humid() + "');";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Expection in writeToDatabase: " + e.toString());
        }
    }

    public static dbType latestEntry() {

        String sql = "SELECT * FROM data ORDER BY time DECS LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            dbType result = new dbType(rs.getString("date"), rs.getString("time"), rs.getFloat("temp"),
                    rs.getFloat("humid"));
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static ArrayList<dbType> getAllEntrys() {

        String sql = "SELECT * FROM data";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<dbType> result = new ArrayList<dbType>();

            while (rs.next()) {
                result.add(new dbType(
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getFloat("temp"),
                        rs.getFloat("humid")));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e.toString());
        }
    }

}
