package com.tmarat.theweather;

public class Data {
    private String cityName;
    private String temperature;
    private String humidity;
    private String press;
    private String wind;

    public Data(String cityName, String temperature, String humidity, String press, String wind) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.press = press;
        this.wind = wind;
    }

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
