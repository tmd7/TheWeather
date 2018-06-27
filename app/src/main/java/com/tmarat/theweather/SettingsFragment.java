package com.tmarat.theweather;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class SettingsFragment extends Fragment {

  private static final String MAIN_CONFIG = "mainConfig";
  public static final String HUM = "hum";
  public static final String PRESS = "press";
  public static final String WIND = "wind";

  private CheckBox hum;
  private CheckBox press;
  private CheckBox wind;

  private SharedPreferences preferences;

  public SettingsFragment() {
    this.preferences = getContext().getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    setUI(view);
    loadPreferences();
    setButtonOnClickListener(view);
    return view;
  }

  private void setUI(View view) {
    hum = view.findViewById(R.id.checkbox_hum);
    press = view.findViewById(R.id.checkbox_press);
    wind = view.findViewById(R.id.checkbox_wind);
  }

  private void loadPreferences() {
    if (preferences != null) {
     hum.setChecked(preferences.getBoolean(HUM, false));
     press.setChecked(preferences.getBoolean(PRESS, false));
     wind.setChecked(preferences.getBoolean(WIND, false));
    }
  }

  private void savePreferences() {
    preferences = getContext().getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(HUM, hum.isChecked());
    editor.putBoolean(PRESS, press.isChecked());
    editor.putBoolean(WIND, wind.isChecked());
    editor.apply();
  }

  private void setButtonOnClickListener(View view) {
    view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        savePreferences();
      }
    });
  }
}
