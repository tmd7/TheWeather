package com.tmarat.theweather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment {
    private TextView cityName;
    private TextView tem;
    private TextView hum;
    private TextView press;
    private TextView wind;

    public static WeatherInfoFragment init (Data data) {
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
        setUI(view);
        return view;
    }

    private void setUI(View view) {
        cityName = view.findViewById(R.id.city_name);
        tem = view.findViewById(R.id.value_tem);
        hum = view.findViewById(R.id.value_hum);
        press = view.findViewById(R.id.value_press);
        wind = view.findViewById(R.id.value_wind);
    }


    public void setWeatherInfo(Data data) {
        cityName.setText(data.getCityName());
        tem.setText(data.getTemperature());
        hum.setText(data.getHumidity());
        press.setText(data.getPress());
        wind.setText(data.getPress());
    }
}
