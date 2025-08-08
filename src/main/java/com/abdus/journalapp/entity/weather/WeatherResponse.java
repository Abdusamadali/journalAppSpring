package com.abdus.journalapp.entity.weather;



import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {

    // Root class
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public int timezone;
    public long id;
    public String name;
    public int cod;


    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Main {
        public double temp;

        @JsonProperty("feels_like")
        public double feelsLike;

        @JsonProperty("temp_min")
        public double tempMin;

        @JsonProperty("temp_max")
        public double tempMax;

        public int pressure;
        public int humidity;

        @JsonProperty("sea_level")
        public int seaLevel;

        @JsonProperty("grnd_level")
        public int groundLevel;
    }

    public static class Wind {
        public double speed;
        public int deg;
        public double gust;
    }

    public static class Clouds {
        public int all;
    }

    public static class Sys {
        public String country;
        public long sunrise;
        public long sunset;
    }


}
