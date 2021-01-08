package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public Main() throws SQLException{}

    public static void main(String[] args) throws Exception {
        launch(args);
        try{
            Connection con;
            con=DriverManager.getConnection("jdbc:derby:./db", "user", "pass");
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Books("
                    +"ISBN INT NOT NULL,"
                    +"title varchar (255) NOT NULL,"
                    +"subject varchar (255) NOT NULL,"
                    +"author varchar (255) NOT NULL,"
                    +"publishDate date NOT NULL,"
                    +"PRIMARY KEY (ISBN));";
            stmt.executeUpdate(query);
            query = "CREATE TABLE IF NOT EXISTS BORROWED_BOOKS("
                    +"userName varchar (255) NOT NULL,"
                    +"borrowedBookName varchar (255) NOT NULL,"
                    +"borrowedBookIsbn INT NOT NULL,"
                    +"returnDate date NOT NULL,"
                    +"returned BIT(1));";
            stmt.executeUpdate(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
