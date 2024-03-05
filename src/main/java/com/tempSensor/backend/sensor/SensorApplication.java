package com.tempSensor.backend.sensor;

public class SensorApplication {

    public static void CollectSensorData() throws InterruptedException {
        int count = 0;

        while (true) {
            System.out.println(count);
            java.lang.Thread.sleep(1000);
        }
    }

}
