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
import java.sql.*;
import java.util.ArrayList;


public class Controller {
    public static int role;
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

    //Admin Borrowed Books AddMode Items
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtISBN;
    @FXML
    TextField txtTitle;
    @FXML
    TextField txttakenDate;
    @FXML
    TextField txtReturnDate;


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
    private TableColumn<Books, Integer> tcISBN;
    @FXML
    private TableColumn<Books, String> tcTitle;
    @FXML
    private TableColumn<Books, String> tcAuthor;
    @FXML
    private TableColumn<Books, String> tcSubject;
    @FXML
    private TableColumn<Books, String> tcPublishDate;
    public static Librarian libGlobal=null;
    public static Students studentGlobal=null;
    public static Books bookGlobal=null;
    public  static int mode;//users mode is 0, books mode is 1, registration mode is 2

    //Librarian Add Window Items



    @FXML
    private void onLogin(ActionEvent event) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM Users");
        ResultSet getStmt = get.executeQuery();

        if (!txtLogin.getText().equals(null) && !txtPassword.getText().equals(null)) {

            while (getStmt.next()) {

                if (getStmt.getString("Login").equals(txtLogin.getText()) && txtPassword.getText().equals(getStmt.getString("Password"))) {

                    if (getStmt.getInt("Role") == 0) {
                        openPage(event, "admin");
                        role = 0;
                    } else if (getStmt.getInt("Role") == 1) {
                       openPage(event, "librarian");
                        role = 1;
                    } else if (getStmt.getInt("Role") == 2) {
                        openPage(event, "student");
                        role = 2;
                    }
                    break;
                } else
                    lblInfo.setText("Wrong login and password");
            }
            con.close();
        }
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
    public void onLibrarians() throws Exception {

        tcIdL.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNameL.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastNameL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmailL.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Librarian> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM Users WHERE Role=1");
        ResultSet getStmt = get.executeQuery();
        while (getStmt.next()) {
            list.add(new Librarian(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password")));
        }
        for (Librarian u : list) {
            tbLibs.getItems().add(u);
        }
        tbLibs.setVisible(true);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onStudents() throws Exception {

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Students> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM Users WHERE Role=2");
        ResultSet getStmt = get.executeQuery();

        while (getStmt.next()) {
            list.add(new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), getStmt.getInt("Role")));
        }
        for (Students u : list) {
            tbStudents.getItems().add(u);
        }
        tbLibs.setVisible(false);
        tbStudents.setVisible(true);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);

    }

    @FXML
    public void onBooks() throws SQLException {

        tcISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        tcPublishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        ArrayList<Books> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM BOOKS");
        ResultSet getStmt = get.executeQuery();
        while (getStmt.next()) {
                list.add(new Books(getStmt.getInt("ISBN"), getStmt.getString("title"), getStmt.getString("subject"), getStmt.getString("author"), getStmt.getString("publishDate")));
        }
        for (Books u : list) {
            tbBooks.getItems().add(u);
        }
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(true);
        lblGreetAdmin.setVisible(false);

    }



    @FXML
    public void onModify(ActionEvent event) throws Exception {
        if (tbStudents.isVisible() || tbLibs.isVisible()) {   //if Students table opened = Student Add List opened
            if (tbLibs.isVisible()) libGlobal=(Librarian)  tbLibs.getSelectionModel().getSelectedItem();
            else if(tbStudents.isVisible()) studentGlobal=(Students)tbStudents.getSelectionModel().getSelectedItem();
            openPage(event, "AddUsers");
            role=0 ;
            mode=0;

        } else if (tbBooks.isVisible()) {  //if Books table opened = Books Add List opened
            bookGlobal=(Books)tbBooks.getSelectionModel().getSelectedItem();
            openPage(event, "AddBooks");

            mode=1;
        }
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);

    }

    @FXML
    public void onDelete() throws Exception{
        PreparedStatement delete=null;
        if (tbLibs.isVisible()) {
            libGlobal=(Librarian)  tbLibs.getSelectionModel().getSelectedItem();
            Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
            delete= con.prepareStatement("DELETE FROM Users WHERE UsersId="+libGlobal.getId());
        }
        else if(tbStudents.isVisible()){
            studentGlobal=(Students)tbStudents.getSelectionModel().getSelectedItem();
            Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
            delete= con.prepareStatement("DELETE FROM Users WHERE UsersId="+studentGlobal.getId());
        }
        else if (tbBooks.isVisible()) {
            bookGlobal = (Books) tbBooks.getSelectionModel().getSelectedItem();
            Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
            delete= con.prepareStatement("DELETE FROM Books WHERE ISBN="+bookGlobal.getISBN());
        }
        delete.execute();
        lblGreetAdmin.setVisible(false);
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);

    }

    ///librarian window methods
    @FXML
    public void onLStudents() {
        tbStudents.setVisible(true);
        tbBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);

        ///librarian window methods
    }

    @FXML
    public void onLBooks() {
        tbStudents.setVisible(false);
        tbBooks.setVisible(true);
        lblGreetLibrarian.setVisible(false);

    }

    @FXML
    public void onLAdd() {
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(false);
        btnLModify.setDisable(true);
        btnLDelete.setDisable(true);
    }

    @FXML
    private void onLModify() {
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(true);
        btnLModify.setDisable(false);
        btnLDelete.setDisable(true);
    }

    @FXML
    private void onLDelete() {
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        btnLAdd.setDisable(true);
        btnLModify.setDisable(true);
        btnLDelete.setDisable(false);
    }

    //Main Add Button
    @FXML
    private void onAdd(ActionEvent event) throws Exception {
        System.out.println(tbBooks.isVisible()+" "+tbStudents.isVisible());
            if (tbStudents.isVisible() || tbLibs.isVisible()) {   //if Students table opened = Student Add List opened
            openPage(event, "AddUsers");
            role=0 ;
            mode=0;
        } else if (tbBooks.isVisible()) {  //if Books table opened = Books Add List opened
            openPage(event, "AddBooks");
            mode=1;

        }
    }


    public static void openPage(ActionEvent event, String page) throws  Exception{
        Parent root = FXMLLoader.load(Controller.class.getResource(page+".fxml"));
        Scene tableView = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(tableView);
        stage.show();
    }
}
