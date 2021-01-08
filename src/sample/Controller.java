package sample;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



import javafx.stage.Stage;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    @FXML
    TextField txtLogin;
    @FXML
    TextField txtPassword;
    @FXML
    Label lblInfo;





    //Librarian Students AddMode Items (Used also in Admin Students AddMode)
    @FXML
    TextField txtSName;
    @FXML
    TextField txtSLastName;
    @FXML
    TextField txtSEmail;
    @FXML
    TextField txtSLogin;
    @FXML
    TextField txtSPassword;

    //Librarian Books AddMode Items (Used also in Admin Students AddMode)
    @FXML
    TextField txtBISBN;
    @FXML
    TextField txtBTitle;
    @FXML
    TextField txtBAuthor;
    @FXML
    TextField txtBSubject;
    @FXML
    TextField txtBPublishDate;
    @FXML
    TextField txtBStatus;

    //Admin Librarian AddMode Items
    @FXML
    TextField txtLID;
    @FXML
    TextField txtLName;
    @FXML
    TextField txtLFamilyName;
    @FXML
    TextField txtLEmail;


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
    private Button btnCancel;
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



        if (!txtLogin.getText().equals(null) && !txtPassword.getText().equals(null)) {

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
        Parent root2 = FXMLLoader.load(getClass().getResource("ui/login.fxml"));
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

    //** Librarian section add/modify buttons

    //Students Adding Window
    @FXML

    private void onLAdd(ActionEvent event) throws Exception {
        if (tbLStudents.isVisible()) {   //if the students table is opened the button "Add" opens Student list to add
            Parent root = FXMLLoader.load(getClass().getResource("libAddButton.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();
        }

        else if (tbLBooks.isVisible()) {   //if books table opened = Books Add List opened
            Parent root = FXMLLoader.load(getClass().getResource("libBooksAddButton.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();
        }

    }

    @FXML
    private void onCancelLStudent(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();

    }

    @FXML
    private void onAddLStudent(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }

    //Books Adding Window Buttons

    @FXML
    private void onCancelLBook(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }
    @FXML
    private void onAddLBook(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }

    //Administrator Adding Button
    @FXML
    private void onAdd(ActionEvent event) throws Exception {
        if (tbLibs.isVisible()) {   //if the librarian table is opened the button "Add" opens Librarian list to add
            Parent root = FXMLLoader.load(getClass().getResource("librarianAddButton.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();
        }

        else if (tbStudents.isVisible()) {   //if Students table opened = Student Add List opened
            Parent root = FXMLLoader.load(getClass().getResource("adminStudentAddButton.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();
        }
        else if (tbBooks.isVisible()){  //if Books table opened = Books Add List opened
            Parent root = FXMLLoader.load(getClass().getResource("adminBooksAddButton.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();
        }

    }
    // Add/Cancel Buttons for librarian section adding mode
    @FXML
    private void onAAdd(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }
    @FXML
    private void onACancel(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }
    // Add/Cancel Buttons for student section adding mode
    @FXML
    private void onAAddStudent(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }
    @FXML
    private void onACancelStudent(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }
    // Add/Cancel Buttons for books section adding mode
    @FXML
    private void onAAddBooks(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }
    @FXML
    private void onACancelBooks(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();


    }




    // Librarian Modify Button
    





}
