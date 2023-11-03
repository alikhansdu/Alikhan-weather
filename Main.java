package com.example.wheatherapp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(new File("C:\\Users\\ACER\\IdeaProjects\\WheatherApp1\\src\\main\\java\\com\\example\\wheatherapp1\\weathercon.fxml").toURI().toURL());
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    }

