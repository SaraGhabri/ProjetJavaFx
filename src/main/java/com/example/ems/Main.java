package com.example.ems;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ems/home-page.fxml"));

            // Set the scene
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Employee Management System");
            primaryStage.setScene(scene);

            // Show the stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
