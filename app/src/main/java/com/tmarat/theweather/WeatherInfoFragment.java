package com.tmarat.theweather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment {
  private TextView labelHum;
  private TextView labelPress;
  private TextView labelWind;

  private TextView cityName;
  private TextView tem;
  private TextView hum;
  private TextView press;
  private TextView wind;

  private Settings settings;

  public static WeatherInfoFragment init(Data data) {
    WeatherInfoFragment fragment = new WeatherInfoFragment();
    Bundle args = new Bundle();
    args.putParcelable("weatherInfo", data);
    fragment.setArguments(args);
    return fragment;
  }

  public Data getData() {
    return getArguments().getParcelable("weatherInfo");
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
    settings = new Settings(view.getContext());
    setUI(view);
    savedInstanceState = getFragmentManager().findFragmentById(R.id.main_container).getArguments();
    if (savedInstanceState != null) {
      setWeatherInfo(getData());
    }
    return view;
  }

  private void setUI(View view) {
    labelHum = view.findViewById(R.id.label_hum);
    labelPress = view.findViewById(R.id.label_press);
    labelWind = view.findViewById(R.id.label_wind);

    cityName = view.findViewById(R.id.city_name);
    tem = view.findViewById(R.id.value_tem);
    hum = view.findViewById(R.id.value_hum);
    press = view.findViewById(R.id.value_press);
    wind = view.findViewById(R.id.value_wind);

    checkSettings();
  }

  private void checkSettings() {
    if (settings.getPreferences() != null) {
      if (settings.getCheckedHum()) {
        labelHum.setVisibility(View.VISIBLE);
        hum.setVisibility(View.VISIBLE);
      } else {
        labelHum.setVisibility(View.GONE);
        hum.setVisibility(View.GONE);
      }

      if (settings.getCheckedPress()) {
        labelPress.setVisibility(View.VISIBLE);
        press.setVisibility(View.VISIBLE);
      } else {
        labelPress.setVisibility(View.GONE);
        press.setVisibility(View.GONE);
      }

      if (settings.getCheckedWind()) {
        labelWind.setVisibility(View.VISIBLE);
        wind.setVisibility(View.VISIBLE);
      } else {
        labelWind.setVisibility(View.GONE);
        wind.setVisibility(View.GONE);
      }
    }
  }

  public void setWeatherInfo(Data data) {
    cityName.setText(data.getCityName());
    tem.setText(data.getTemperature());
    hum.setText(data.getHumidity());
    press.setText(data.getPress());
    wind.setText(data.getWind());
  }
}
