package com.tempSensor.backend.endpoints;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tempSensor.backend.database.models.dbType;
import com.tempSensor.backend.sensor.models.sensorData;
import com.tempSensor.backend.database.functions.Functions;
import com.tempSensor.backend.database.debug.DebugController;

@RestController
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
    public ArrayList<dbType> allEntrys() {
        try {
            return Functions.getAllEntrys();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    @GetMapping("/getNumberOfentrys")
    public int getNumberOfentrys() {
        return DebugController.getNumberOfentrys();
    }

    @PostMapping("/postNewDataToDatabase")
    public String postNewDataToDatabase(@RequestBody sensorData data) {
        try {
            Functions.writeToDatabase(data);
            return "Success";
        } catch (Exception e) {
            return "Exceptoion: " + e.toString();
        }
    }

}
