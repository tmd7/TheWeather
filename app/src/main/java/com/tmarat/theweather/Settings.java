package com.tmarat.theweather;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
  private static final String MAIN_CONFIG = "mainConfig";
  private static final String HUM = "hum";
  private static final String PRESS = "press";
  private static final String WIND = "wind";

  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  private Boolean isCheckedHum;
  private Boolean isCheckedPress;
  private Boolean isCheckedWind;

  Settings(Context context) {
    preferences = context.getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE);
    this.isCheckedHum = preferences.getBoolean(HUM, false);
    this.isCheckedPress = preferences.getBoolean(PRESS, false);
    this.isCheckedWind = preferences.getBoolean(WIND, false);
  }

  public Boolean getCheckedHum() {
    return isCheckedHum;
  }

  public Boolean getCheckedPress() {
    return isCheckedPress;
  }

  public Boolean getCheckedWind() {
    return isCheckedWind;
  }

  public void setCheckedHum(Boolean checkedHum) {
    isCheckedHum = checkedHum;
    editor = preferences.edit();
    editor.putBoolean(HUM, isCheckedHum);
    editor.apply();
  }

  public void setCheckedPress(Boolean checkedPress) {
    isCheckedPress = checkedPress;
    editor = preferences.edit();
    editor.putBoolean(PRESS, isCheckedPress);
    editor.apply();
  }

  public void setCheckedWind(Boolean checkedWind) {
    isCheckedWind = checkedWind;
    editor = preferences.edit();
    editor.putBoolean(WIND, isCheckedWind);
    editor.apply();
  }

  public SharedPreferences getPreferences() {
    return preferences;
  }
}
