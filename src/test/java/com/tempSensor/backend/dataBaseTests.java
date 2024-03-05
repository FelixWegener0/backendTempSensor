package com.tempSensor.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.tempSensor.backend.database.functions.Functions;
import com.tempSensor.backend.database.models.dbType;

public class dataBaseTests {

    @Test
    public void testLatestEntry() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        String expectedSql = "SELECT * FROM date LIMIT 1 ORDER BY date ORDER BY time";

        when(rs.getString("date")).thenReturn("2024-03-03");
        when(rs.getString("time")).thenReturn("12:00:00");
        when(rs.getFloat("temp")).thenReturn(20.0f);
        when(rs.getFloat("humid")).thenReturn(50.0f);

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        }

        when(DriverManager.getConnection(anyString())).thenReturn(conn);

        when(conn.createStatement()).thenReturn(stmt);

        when(stmt.executeQuery(expectedSql)).thenReturn(rs);

        dbType result = Functions.latestEntry();

        assertEquals("2024-03-03", result.date());
        assertEquals("12:00:00", result.time());
        assertEquals(20.0f, result.temp(), 0.001);
        assertEquals(50.0f, result.humid(), 0.001);
    }

}
