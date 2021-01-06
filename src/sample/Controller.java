package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Controller {
    @FXML
    TextField txtLogin;
    @FXML
    TextField txtPassword;


    @FXML
    private void onLogin (ActionEvent event) throws Exception{
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ResultSet getStmt= databaseConnection.getStmt("Users");

        while (getStmt.next()){
            System.out.println(getStmt.getString("Login"));
            System.out.println(txtLogin.getText());
            System.out.println(getStmt.getString("Password"));
            System.out.println(txtPassword.getText());
            if (getStmt.getString("Login").equals(txtLogin.getText())  && txtPassword.getText().equals(getStmt.getString("Password"))   ){
                Parent root = FXMLLoader.load(getClass().getResource("librarians.fxml"));
                Scene tableView = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(tableView);
                stage.show();
            }
            else{
                txtLogin.setText(txtPassword.getText());
            }
        }

    }
}
