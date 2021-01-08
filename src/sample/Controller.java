package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
<<<<<<< HEAD
import java.sql.*;
import java.util.ArrayList;

=======
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
>>>>>>> fd1175c0652b24b314c08c10ae0eb71c12c948b9

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
    private TableColumn<Students, Integer> tcId;
    @FXML
    private TableColumn<Students, String> tcName;
    @FXML
    private TableColumn<Students, String> tcLastName;
    @FXML
    private TableColumn<Students, String> tcEmail;
    @FXML
    private TableColumn<Students, Integer> tcIdL;
    @FXML
    private TableColumn<Students, String> tcNameL;
    @FXML
    private TableColumn<Students, String> tcLastNameL;
    @FXML
    private TableColumn<Students, String> tcEmailL;
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
        Connection con= DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get= con.prepareStatement("SELECT * FROM Users");
        ResultSet getStmt=get.executeQuery() ;

<<<<<<< HEAD
        if (txtLogin.getText() != null && txtPassword.getText() != null) {
=======

        if (!txtLogin.getText().equals(null) && !txtPassword.getText().equals(null)) {
>>>>>>> fd1175c0652b24b314c08c10ae0eb71c12c948b9
            while (getStmt.next()) {

                if (getStmt.getString("Login").equals(txtLogin.getText()) && txtPassword.getText().equals(getStmt.getString("Password"))) {

                    if (getStmt.getInt("Role") == 0) {
                        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene tableView = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(tableView);
                        stage.show();
                    }
                    else if (getStmt.getInt("Role") == 1) {
                        Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
                        Scene tableView = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(tableView);
                        stage.show();
                    }
                    else if (getStmt.getInt("Role") == 2) {
                        Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
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
        con.close();
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
    public void onLibrarians() throws Exception{

        tcIdL.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNameL.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastNameL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmailL.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Librarian> list=new ArrayList<>();
        Connection con= DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get= con.prepareStatement("SELECT * FROM Users WHERE Role=1");
        ResultSet getStmt=get.executeQuery() ;
        while (getStmt.next()){
            list.add(new Librarian(getStmt.getInt("UsersId"), getStmt.getString("FirstName"),getStmt.getString("LastName"),getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password")));
        }
        for (Librarian u: list){
            tbLibs.getItems().add(u);
        }
        tbLibs.setVisible(true);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onStudents() throws  Exception{

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Students> list=new ArrayList<>();
        Connection con= DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get= con.prepareStatement("SELECT * FROM Users WHERE Role=2");
        ResultSet getStmt=get.executeQuery() ;

        while (getStmt.next()){
            list.add(new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"),getStmt.getString("LastName"),getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), getStmt.getInt("Role")));
        }
        for (Students u: list){
            tbStudents.getItems().add(u);
        }
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
