package com.tempSensor.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tempSensor.backend.sensor.SensorApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) throws InterruptedException {

		Thread collectSensorDataThread = new Thread(() -> {
			try {
				SensorApplication.CollectSensorData();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread springApplicationThread = new Thread(() -> SpringApplication.run(BackendApplication.class, args));

		collectSensorDataThread.setDaemon(true);
		springApplicationThread.setDaemon(false);

		collectSensorDataThread.start();
		springApplicationThread.start();
	}

}
