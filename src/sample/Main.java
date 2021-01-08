package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application  implements Runnable{

    @Override
<<<<<<< HEAD
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
=======
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/login.fxml"));
>>>>>>> 265959e479d02cf23fda781b437ea25a1399c804
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 560, 400));
        primaryStage.show();
    }

    public Main() throws SQLException{}

<<<<<<< HEAD
    public static void main(String[] args) throws Exception {
=======
    public static void main(String[] args) {
        Main obj = new Main();
        Thread thread= new Thread(obj);
        thread.start();
>>>>>>> 265959e479d02cf23fda781b437ea25a1399c804
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
    public void run() {
    }
}
