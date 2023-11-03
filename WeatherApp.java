package com.example.wheatherapp1;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apiKey = "22b4a4a73e2e17d6294252d025c6e87b";
        System.out.print("City name: ");
        String city = sc.nextLine();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
            HttpGet httpGet = new HttpGet(apiUrl);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                JSONObject data = new JSONObject(responseBody);
                if (data.has("weather")) {
                    String weatherDescription = data.getJSONArray("weather").getJSONObject(0).getString("description");
                    double temperature = data.getJSONObject("main").getDouble("temp");


                    System.out.println("City: " + city);
                    System.out.println("Weather: " + weatherDescription);
                    System.out.println("Temperature: " + temperature + " °C");
                } else {
                    System.out.println("Weather data not found for the specified city.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
