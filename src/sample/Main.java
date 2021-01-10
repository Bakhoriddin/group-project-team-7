package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application  implements Runnable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 560, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        Main obj = new Main();
        Thread thread = new Thread(obj);
        thread.start();
        launch(args);
    }

    public void run() {
    }
}
