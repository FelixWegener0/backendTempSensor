package com.tempSensor.backend.endpoints;

import org.springframework.web.bind.annotation.GetMapping;

import com.tempSensor.backend.database.models.dbType;
import com.tempSensor.backend.database.functions.Functions;

public class DataBaseApiController {

    @GetMapping("/getLatestEntry")
    public dbType latestEntry() {
        try {
            return Functions.latestEntry();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    @GetMapping("/getAllEntrys")
    public dbType[] allEntrys() {
        try {
            return Functions.getAllEntrys();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

}
