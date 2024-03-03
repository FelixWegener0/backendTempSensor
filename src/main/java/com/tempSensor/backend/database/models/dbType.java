package com.tempSensor.backend.database.models;

public record dbType(
        String date,
        String time,
        float temp,
        float humid) {
}
