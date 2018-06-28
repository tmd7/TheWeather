package com.tmarat.theweather.common;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.tmarat.theweather.data.Data;

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
    data = new Data();
    data.setCityName("Ambient weather");
    data.setWind("0");
    checkSensor();
    registerSensorListener();
  }

  public boolean checkSensor() {
    sensorTem = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    sensorHum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    sensorPress = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

    if (sensorTem == null) {
      data.setTemperature("0");
    }
    if (sensorHum == null) {
      data.setHumidity("0");
    }
    if (sensorPress == null) {
      data.setPress("0");
    }

    return sensorTem != null || sensorHum != null || sensorPress != null;
  }

  private void registerSensorListener() {
    sensorManager.registerListener(sensorListener, sensorTem, SensorManager.SENSOR_DELAY_NORMAL);
    sensorManager.registerListener(sensorListener, sensorHum, SensorManager.SENSOR_DELAY_NORMAL);
    sensorManager.registerListener(sensorListener, sensorPress, SensorManager.SENSOR_DELAY_NORMAL);
  }

  public void unregisterSensorListener() {
    sensorManager.unregisterListener(sensorListener,sensorTem);
    sensorManager.unregisterListener(sensorListener,sensorHum);
    sensorManager.unregisterListener(sensorListener,sensorPress);
  }

  private SensorEventListener sensorListener = new SensorEventListener() {
    @Override public void onSensorChanged(SensorEvent event) {
      switch (event.sensor.getType()) {
        case Sensor.TYPE_AMBIENT_TEMPERATURE:
          Log.d("sensor", "tem");
          data.setTemperature(toString(event.values[0]));
          break;

        case Sensor.TYPE_RELATIVE_HUMIDITY:
          Log.d("sensor", "hum");
          data.setHumidity(toString(event.values[0]));
          break;

        case Sensor.TYPE_PRESSURE:
          Log.d("sensor", "press");
          data.setPress(toString(event.values[0]));
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
