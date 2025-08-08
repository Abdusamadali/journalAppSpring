package com.abdus.journalapp.service;



import com.abdus.journalapp.entity.weather.WeatherResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String KEY ;


    private RestTemplate rt =new RestTemplate();



    String city = "delhi";
//    String url = "https://api.openweathermap.org/data/2.5/weather?q=delhi&appid=54b3950ea9fc98b8b0bbda3000455eb2";

    public WeatherResponse ApiCall(String city) {

        this.city = city;
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, KEY);


        return rt.getForObject(url, WeatherResponse.class);
    }

}

