package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.ResultSet;

public class Controller {
    @FXML
    TextField txtLogin;
    @FXML
    TextField txtPassword;
    @FXML
    Label lblInfo;

    @FXML
    private void onLogin(ActionEvent event) throws Exception {
        ResultSet getStmt = DatabaseConnection.getStmt("Users");
        if (txtLogin.getText() != null && txtPassword.getText() != null) {
            while (getStmt.next()) {

                if (getStmt.getString("Login").equals(txtLogin.getText()) && txtPassword.getText().equals(getStmt.getString("Password"))) {

                    if (getStmt.getInt("Role") == 0) {
                        Parent root = FXMLLoader.load(getClass().getResource("librarians.fxml"));
                        Scene tableView = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(tableView);
                        stage.show();
                    }
                    break;
                } else {
                    lblInfo.setText("Wrong login and password");

                }


            }


        }
    }
}
