package com.tmarat.theweather;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable{
    private String cityName;
    private String temperature;
    private String humidity;
    private String press;
    private String wind;

    Data(String cityName, String temperature, String humidity, String press, String wind) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.press = press;
        this.wind = wind;
    }

    private Data(Parcel in) {
        cityName = in.readString();
        temperature = in.readString();
        humidity = in.readString();
        press = in.readString();
        wind = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeString(temperature);
        dest.writeString(humidity);
        dest.writeString(press);
        dest.writeString(wind);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPress() {
        return press;
    }

    public String getWind() {
        return wind;
    }
}
