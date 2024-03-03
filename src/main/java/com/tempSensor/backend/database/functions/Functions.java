package com.tempSensor.backend.database.functions;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalTime;
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

        String sql = "INSERT INTO date (date, time, temp, humid) VALUES ('" + date + "', '" + time
                + "', '" + data.temp() + "', '" + data.humid() + "');";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Expection in writeToDatabase: " + e.toString());
        }
    }

    public static dbType latestEntry() {

        String sql = "SELECT * FROM date LIMIT 1 ORDER BY date ORDER BY time";

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

    public static dbType[] getAllEntrys() {

        String sql = "SELECT * FROM data";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            int columnCount = rs.getMetaData().getColumnCount();
            dbType[] result = new dbType[columnCount];

            for (int i = 0; i < columnCount; i++) {
                result[i] = new dbType(rs.getString("date"), rs.getString("time"), rs.getFloat("temp"),
                        rs.getFloat("humid"));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e.toString());
        }
    }

}
