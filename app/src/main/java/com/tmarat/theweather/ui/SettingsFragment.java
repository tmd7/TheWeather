package com.tmarat.theweather.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.tmarat.theweather.R;
import com.tmarat.theweather.common.Settings;

public class SettingsFragment extends Fragment {

  private CheckBox hum;
  private CheckBox press;
  private CheckBox wind;

  private Settings settings;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    settings = new Settings(view.getContext());
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
    if (settings.getPreferences() != null) {
      hum.setChecked(settings.getCheckedHum());
      press.setChecked(settings.getCheckedPress());
      wind.setChecked(settings.getCheckedWind());
    } else {
      savePreferences();
    }
  }

  private void savePreferences() {
    settings.setCheckedHum(hum.isChecked());
    settings.setCheckedPress(press.isChecked());
    settings.setCheckedWind(wind.isChecked());
  }

  private void setButtonOnClickListener(View view) {
    view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        savePreferences();
      }
    });
  }
}
