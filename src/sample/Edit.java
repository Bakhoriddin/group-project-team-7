package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class Edit implements Initializable {
    private int id;
    // Items Users
    @FXML
    private RadioButton rbStudent;
    @FXML
    private RadioButton rbLibrarian;
    @FXML
    private TextField txtSName;
    @FXML
    private TextField txtSLastName;
    @FXML
    private TextField txtSEmail;
    @FXML
    private TextField txtSLogin;
    @FXML
    private TextField txtSPassword;
    @FXML
    private Label lblInfo;
    //Items Books
    @FXML
    TextField txtBISBN;
    @FXML
    TextField txtBTitle;
    @FXML
    TextField txtBAuthor;
    @FXML
    TextField txtBSubject;
    @FXML
    DatePicker txtBPublishDate;
    @FXML
    TextField txtBStatus;
    @FXML
    private Label lblInfoBook;

    private void back(ActionEvent event) throws Exception{
        if (Controller.role == 0) {
            Controller.openPage(event, "Admin");

        } else if (Controller.role == 1) {
            Controller.openPage(event, "librarian");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(rbLibrarian != null) {
            if (Controller.role == 1) {
                rbLibrarian.setVisible(false);
                rbStudent.setVisible(false);
            }
        }
        if (Controller.libGlobal!=null){
            id= Controller.libGlobal.getId();
            txtSName.setText(Controller.libGlobal.getFirstName());
            txtSLastName.setText(Controller.libGlobal.getLastName());
            txtSEmail.setText(Controller.libGlobal.getEmail());
            txtSLogin.setText(Controller.libGlobal.getLogin());
            txtSPassword.setText(Controller.libGlobal.getPassword());
            rbLibrarian.setSelected(true);

        }
        else if (Controller.studentGlobal!=null){
            id= Controller.studentGlobal.getId();
            txtSName.setText(Controller.studentGlobal.getFirstName());
            txtSLastName.setText(Controller.studentGlobal.getLastName());
            txtSEmail.setText(Controller.studentGlobal.getEmail());
            txtSLogin.setText(Controller.studentGlobal.getLogin());
            txtSPassword.setText(Controller.studentGlobal.getPassword());
            rbStudent.setSelected(true);
        }
        else if(Controller.bookGlobal!=null){
             txtBISBN.setText(String.valueOf( Controller.bookGlobal.getISBN()));
             txtBTitle.setText(Controller.bookGlobal.getTitle());
            txtBSubject.setText(Controller.bookGlobal.getSubject());
            txtBAuthor.setText(Controller.bookGlobal.getAuthor());
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date= LocalDate.parse(Controller.bookGlobal.getPublishDate(), formatter);
            txtBPublishDate.setValue(date);
        }
    }
     @FXML
    public void onEdit(ActionEvent event) throws Exception {

         Connection con=null;
         if(Controller.mode==0) con= DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
         if(Controller.mode==1) con= DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
         if (Controller.libGlobal!=null || Controller.studentGlobal!=null){
             String firstName = txtSName.getText();
             String lastName = txtSLastName.getText();
             String email = txtSEmail.getText();
             String login = txtSLogin.getText();

             String password = txtSPassword.getText();
             int roleUsers;
             if (rbStudent.isSelected()) roleUsers = 2;
             else if (rbLibrarian.isSelected()) roleUsers = 1;
             else roleUsers = 2;
             PreparedStatement update= con.prepareStatement("UPDATE Users SET FirstName='"+firstName+"' ,LastName='"+lastName+"', Email='"+email+"', Login='"+login+"', Password='"+password+"', Role="+roleUsers+" WHERE UsersId="+id);
            update.execute();
            back(event);
         }
         else if(Controller.bookGlobal!=null){
             int isbn=Integer.parseInt( txtBISBN.getText());
             String title= txtBTitle.getText();
             String subject=txtBSubject.getText();
             String author=txtBAuthor.getText();
             String publishDate=txtBPublishDate.getValue().toString();
             PreparedStatement update= con.prepareStatement("UPDATE Books SET isbn="+isbn+" ,title='"+title+"', subject='"+subject+"', author='"+author+"', publishDate='"+publishDate+"' WHERE ISBN="+Controller.bookGlobal.getISBN());
             update.execute();
             back(event);
         }
         else {
             if (Controller.mode == 0) {
                 String firstName = txtSName.getText();
                 String lastName = txtSLastName.getText();
                 String email = txtSEmail.getText();
                 String login = txtSLogin.getText();

                 String password = txtSPassword.getText();
                 int roleUsers;
                 if (rbStudent.isSelected()) roleUsers = 2;
                 else if (rbLibrarian.isSelected()) roleUsers = 1;
                 else roleUsers = 2;
                 PreparedStatement get = con.prepareStatement("SELECT Login FROM Users");
                 ResultSet resultSet = get.executeQuery();
                 boolean unic = true;
                 if (!firstName.equals(null) && !lastName.equals(null) && !email.equals(null) && !login.equals(null) && !password.equals(null) && (rbStudent.isSelected() || rbLibrarian.isSelected())) {

                     while (resultSet.next()) {
                         if (resultSet.getString("Login").equals(login))
                             unic = false;
                     }
                     if (unic) {
                         PreparedStatement add = con.prepareStatement("INSERT INTO Users (FirstName, LastName, Email, Login, Password, Role) VALUES('" + firstName + "','" + lastName + "', '" + email + "','" + login + "','" + password + "'," + roleUsers + ")");
                         add.execute();
                         back(event);

                     } else
                         lblInfo.setText("Login not unique");


                 } else
                     lblInfo.setText("You didn't fill all fields");
             }
             else if(Controller.mode==1){
                 int isbn=Integer.parseInt( txtBISBN.getText());
                 String title= txtBTitle.getText();
                 String subject=txtBSubject.getText();
                 String author=txtBAuthor.getText();
                 String publishDate=txtBPublishDate.getValue().toString();
                 PreparedStatement add = con.prepareStatement("INSERT INTO Books (ISBN, title, subject, author, publishDate) VALUES(" + isbn + ",'" + title + "', '" + subject + "','" + author + "','" + publishDate +"')");
                 add.execute();
                 back(event);
             }
         }
         con.close();

     }
     @FXML
    private void onRdStudnt(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddUsers.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }

    @FXML
    private void onRdBooks(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddBooks.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }

    @FXML
    private void onRdLbr(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddBorrowedBooks.fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }
    @FXML
    private void onRdSlctStudnt(ActionEvent event) throws Exception {
        //choose to add student or librarian
    }

    @FXML
    private void onRdSlctLbr(ActionEvent event) throws Exception {
        //choose to add student or librarian
    }
    @FXML
    private void onCancel(ActionEvent event) throws Exception {
        if (Controller.role == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();

        } else if (Controller.role == 1) {
            Parent root = FXMLLoader.load(getClass().getResource("librarian.fxml"));
            Scene tableView = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableView);
            stage.show();


        }

    }

}
