package com.example.wheatherapp1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class weatherController implements Initializable {
    @FXML
    Label city;
    @FXML
    Label weather;
    @FXML
    Label temperature;
    @FXML
    Button search;
    @FXML
    TextField text;

    private CloseableHttpClient httpClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        httpClient = HttpClients.createDefault();

        search.setOnAction(event -> {
            String cityName = text.getText();
            String apikey = "22b4a4a73e2e17d6294252d025c6e87b";
            String apiurl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apikey;

            try (CloseableHttpResponse response = httpClient.execute(new HttpGet(apiurl))) {
                String responseBody = EntityUtils.toString(response.getEntity());

                JSONObject data = new JSONObject(responseBody);
                if (data.has("weather")) {
                    String weatherDescription = data.getJSONArray("weather").getJSONObject(0).getString("description");
                    double temp = data.getJSONObject("main").getDouble("temp");

                    Platform.runLater(() -> {
                        city.setText("City: " + cityName);
                        weather.setText("Weather: " + weatherDescription);
                        temperature.setText("Temperature: " + temp + " °C");
                    });
                } else {
                    System.out.println("Weather data not found for the specified city.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
