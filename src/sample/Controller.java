package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    TextField txtLogin;
    @FXML
    TextField txtPassword;
    @FXML
    Label lblInfo;

    ///admin window items
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnLibs;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btnBooks;
    @FXML
    private TableView tbLibs;
    @FXML
    private TableView tbStudents;
    @FXML
    private TableView tbBooks;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblGreetAdmin;

    ///librarian window items
    @FXML
    private Button btnLLogout;
    @FXML
    private Button btnLStudents;
    @FXML
    private Button btnLBooks;
    @FXML
    private TableView tbLStudents;
    @FXML
    private TableView tbLBooks;
    @FXML
    private Button btnLAdd;
    @FXML
    private Button btnLModify;
    @FXML
    private Button btnLDelete;
    @FXML
    private Label lblGreetLibrarian;

    @FXML
    private void onLogin(ActionEvent event) throws Exception {
        ResultSet getStmt = DatabaseConnection.getStmt("Users");
        if (txtLogin.getText() != null && txtPassword.getText() != null) {
            while (getStmt.next()) {

                if (getStmt.getString("Login").equals(txtLogin.getText()) && txtPassword.getText().equals(getStmt.getString("Password"))) {

                    if (getStmt.getInt("Role") == 0) {
                        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
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


    ///
    @FXML
    public void onLogout(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window2 = (Stage) btnLogout.getScene().getWindow();
        window2.setScene(new Scene(root2, 562, 399));
    }



    ///admin window methods
    @FXML
    public void onLibrarians() {
        tbLibs.setVisible(true);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onStudents() {
        tbLibs.setVisible(false);
        tbStudents.setVisible(true);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onBooks() {
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(true);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onAdd() {
        btnAdd.setDisable(false);
        btnModify.setDisable(true);
        btnDelete.setDisable(true);
        lblGreetAdmin.setVisible(false);
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);

    }

    @FXML
    public void onModify() {
        btnAdd.setDisable(true);
        btnModify.setDisable(false);
        btnDelete.setDisable(true);
        lblGreetAdmin.setVisible(false);
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);

    }

    @FXML
    public void onDelete() {
        btnAdd.setDisable(true);
        btnModify.setDisable(true);
        btnDelete.setDisable(false);
        lblGreetAdmin.setVisible(false);
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);

    }

    ///librarian window methods
    @FXML
    public void onLStudents() {
        tbLStudents.setVisible(true);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);

    }

    @FXML
    public void onLBooks() {
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(true);
        lblGreetLibrarian.setVisible(false);

    }

    @FXML
    public void onLAdd(){
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(false);
        btnLModify.setDisable(true);
        btnLDelete.setDisable(true);
    }

    @FXML
    public void onLModify(){
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(true);
        btnLModify.setDisable(false);
        btnLDelete.setDisable(true);
    }

    @FXML
    public void onLDelete(){
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(true);
        btnLModify.setDisable(true);
        btnLDelete.setDisable(false);
    }
}
