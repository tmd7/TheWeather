package com.tmarat.theweather;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Sensors {
  private SensorManager sensorManager;
  private Sensor sensorTem;
  private Sensor sensorHum;
  private Sensor sensorPress;
  private Data data;

  public Data getData() {
    return data;
  }

  public Sensors(Context context) {
    this.sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
    data.setCityName("Ambient");
    data.setWind("n/a");
    initSensors();
    registerSensorListener();
    unregisterSensorListener();
  }

  public boolean initSensors() {
    sensorTem = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    sensorHum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    sensorPress = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

    if (sensorTem == null) {
      data.setTemperature("n/a");
    }
    if (sensorHum == null) {
      data.setHumidity("n/a");
    }
    if (sensorPress == null) {
      data.setPress("n/a");
    }

    return sensorTem != null || sensorHum != null || sensorPress != null;
  }

  private void registerSensorListener() {
    sensorManager.registerListener(sensorListener, sensorTem, SensorManager.SENSOR_DELAY_NORMAL);
    sensorManager.registerListener(sensorListener, sensorHum, SensorManager.SENSOR_DELAY_NORMAL);
    sensorManager.registerListener(sensorListener, sensorPress, SensorManager.SENSOR_DELAY_NORMAL);
  }

  private void unregisterSensorListener() {
    sensorManager.unregisterListener(sensorListener,sensorTem);
    sensorManager.unregisterListener(sensorListener,sensorHum);
    sensorManager.unregisterListener(sensorListener,sensorPress);
  }

  private SensorEventListener sensorListener = new SensorEventListener() {
    @Override public void onSensorChanged(SensorEvent event) {
      switch (event.sensor.getType()) {
        case Sensor.TYPE_AMBIENT_TEMPERATURE:
          data.setTemperature(toString(event.values[0]));
          break;

        case Sensor.TYPE_RELATIVE_HUMIDITY:
          data.setHumidity(toString(event.values[0]));
          break;

        case Sensor.TYPE_PRESSURE:
          data.setPress(toString(event.values[0]));
          break;

        default:
          //something
          break;
      }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private String toString(float value) {
      return String.valueOf((int) value);
    }
  };
}
