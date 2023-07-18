package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin
public class WeatherController {

    @Value("${hefeng.key}")
    private String key;

    @GetMapping
    public String  getCurrentWeather(@RequestParam("lon") String lon, @RequestParam("lat")String lat)  throws IOException {
        URL url = new URL("http://api.caiyunapp.com/v2.6/NMFkcX12TkkmxzQq/" + lat + "," + lon +"/weather?alert=true&dailysteps=1&hourlysteps=24");
        return connect(url, false);
    }

    @GetMapping("/hefeng")
    public String  getCurrentWeatherByHefeng(@RequestParam("location") String location, @RequestParam(required = false, value = "key") String key)  throws IOException {
        if(key!= null && !key.equals("")){
            this.key = key;
        }
        URL url = new URL("https://devapi.qweather.com/v7/weather/now?key=" + this.key + "&location=" + location);
        return connect(url, true);
    }

    /**
     * 抽取一下私有方法
     * @param url
     * @return
     * @throws IOException
     */
    private String connect(URL url, Boolean gzip) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in;
            if (gzip){
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "UTF-8"));
            }else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "Error: " + responseCode;
        }
    }

}
