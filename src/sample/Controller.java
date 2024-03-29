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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Controller {
    public static int role;
    @FXML
    TextField txtLogin;
    @FXML
    PasswordField txtPassword;
    @FXML
    Label lblInfo;
    @FXML
    Label lblInfoLogin;

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
    private Label lblGreetLibrarian;
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
    private TableView tbBorrowedBooks;
    @FXML
    private TableView tbDeptor;
    @FXML
    private Button btnLAdd;
    @FXML
    private Button btnLModify;
    @FXML
    private Button btnLDelete;
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
    @FXML
    private TableColumn<Books, Integer> tcISBNL;
    @FXML
    private TableColumn<Books, String> tcTitleL;
    @FXML
    private TableColumn<Books, String> tcAuthorL;
    @FXML
    private TableColumn<Books, String> tcSubjectL;
    @FXML
    private TableColumn<Books, String> tcPublishDateL;
    public static Librarian libGlobal=null;
    public static Students studentGlobal=null;
    public static Books bookGlobal=null;
    public  static int mode;//users mode is 0, books mode is 1, registration mode is 2

    ///student window items
    @FXML
    private Button btnSDetails;
    @FXML
    private Button btnSBooks;
    @FXML
    private TableView tbSbooks;
    @FXML
    private Label lblSName;
    @FXML
    private Label lblSFamName;
    @FXML
    private Label lblSid;
    @FXML
    private Label lblSEmail;
    @FXML
    private Label lblSBorrowed;
    @FXML
    private Button btnSReserve;
    @FXML
    private Label lblCurFine;
    @FXML
    private ListView listsBooks;
    @FXML
    private Label lblGreetStudent;

    @FXML
    private TableColumn<Borrowed, String> tcBISBN;
    @FXML
    private TableColumn<Borrowed, String> tcBTitle;
    @FXML
    private TableColumn<Borrowed, String> tcTaken;
    @FXML
    private TableColumn<Borrowed, String> tcReturn;
    @FXML
    private TableColumn<Borrowed, Boolean> tcBStatus;
    @FXML
    private TableColumn<Borrowed, String> tcBUser;
    @FXML
    private TableColumn<Borrowed, String> tcDReturn;
    @FXML
    private TableColumn<Borrowed, Boolean> tcDTitle;
    @FXML
    private TableColumn<Borrowed, String> tcDUsername;
    //Student Books Details Table Items
    @FXML
    private TableColumn<Books, Integer> tcSISBN;
    @FXML
    private TableColumn<Books, String> tcSTitle;
    @FXML
    private TableColumn<Books, String> tcSAuthor;
    @FXML
    private TableColumn<Books, String> tcSSubject;
    @FXML
    private TableColumn<Books, String> tcSPublishDate;


    @FXML

    //Librarian Add Window Items
    public static Borrowed borrowedGlobal;

    boolean librarianAlreadyExists = false;
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
                        studentGlobal= new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), "");

                        openPage(event, "student");
                        role = 2;
                    }
                    break;
                } else
                    lblInfoLogin.setText("Wrong login and password");
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
        if (!librarianAlreadyExists) {
            while (getStmt.next()) {
                list.add(new Librarian(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password")));
            }
            for (Librarian u : list) {
                tbLibs.getItems().add(u);
            }
            librarianAlreadyExists = true;
        }
        tbLibs.setVisible(true);
        tbStudents.setVisible(false);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);


    }
    boolean studentAlreadyExists;
    @FXML
    public void onStudents() throws Exception {

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Students> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");

        Connection con1 = DriverManager.getConnection("jdbc:derby:./borrowed;", "user", "pass");

        PreparedStatement borrow= con1.prepareStatement("SELECT * FROM BORROWED_BOOKS where returned = false");
        PreparedStatement get = con.prepareStatement("SELECT * FROM Users WHERE Role=2");
        ResultSet getStmt = get.executeQuery();
        ResultSet borrowStmt=borrow.executeQuery();
        if (!studentAlreadyExists) {

            while (getStmt.next()) {
                String taken="";
                while (borrowStmt.next()){
                    if (borrowStmt.getString("userName").equals(getStmt.getString("FirstName")))
                        taken+=borrowStmt.getString("borrowedBookName");
                }
                list.add(new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), taken));
            }
            for (Students u : list) {
                tbStudents.getItems().add(u);
            }
            studentAlreadyExists = true;
        }
        tbLibs.setVisible(false);
        tbStudents.setVisible(true);
        tbBooks.setVisible(false);
        lblGreetAdmin.setVisible(false);


    }
    boolean booksAlreadyExits=false;

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
        if (!booksAlreadyExits) {
            while (getStmt.next()) {
                list.add(new Books(getStmt.getInt("ISBN"), getStmt.getString("title"), getStmt.getString("subject"), getStmt.getString("author"), getStmt.getString("publishDate")));
            }
            for (Books u : list) {
                tbBooks.getItems().add(u);
            }
            booksAlreadyExits = true;
        }
        tbLibs.setVisible(false);
        tbStudents.setVisible(false);
        tbBooks.setVisible(true);
        lblGreetAdmin.setVisible(false);


    }



    @FXML
    public void onModify(ActionEvent event) throws Exception {
        if(tbStudents!=null){
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
            lblGreetAdmin.setVisible(false);
        }
        else if(tbLStudents!=null) {
            if (tbLStudents.isVisible()) {
                studentGlobal = (Students) tbLStudents.getSelectionModel().getSelectedItem();
                openPage(event, "AddUsers");
                role = 1;
                mode = 0;
            } else if (tbLBooks.isVisible()) {
                bookGlobal = (Books) tbLBooks.getSelectionModel().getSelectedItem();
                openPage(event, "AddBooks");
                mode = 1;
            }
            else if(tbBorrowedBooks.isVisible()){
                borrowedGlobal=(Borrowed)tbBorrowedBooks.getSelectionModel().getSelectedItem();
                openPage(event, "AddBorrowedBooks");
                mode=2;
            }
            tbLStudents.setVisible(false);
            tbLBooks.setVisible(false);
            lblGreetLibrarian.setVisible(false);
        }


    }

    @FXML
    public void onDelete() throws Exception{
        PreparedStatement delete=null;
        PreparedStatement get = null;
        ResultSet getStmt = null;
        int role = 0;
        ArrayList<Librarian> list1 = new ArrayList<>();
        ArrayList<Students> list2 = new ArrayList<>();
        ArrayList<Books> list3 = new ArrayList<>();
        if(tbStudents!=null) {
            if (tbLibs.isVisible()) {
                libGlobal = (Librarian) tbLibs.getSelectionModel().getSelectedItem();
                Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
                delete = con.prepareStatement("DELETE FROM Users WHERE UsersId=" + libGlobal.getId());
                role = 1;
            } else if (tbStudents.isVisible()) {
                studentGlobal = (Students) tbStudents.getSelectionModel().getSelectedItem();
                Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
                delete = con.prepareStatement("DELETE FROM Users WHERE UsersId=" + studentGlobal.getId());
                role = 2;
            } else if (tbBooks.isVisible()) {
                bookGlobal = (Books) tbBooks.getSelectionModel().getSelectedItem();
                Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
                delete = con.prepareStatement("DELETE FROM Books WHERE ISBN=" + bookGlobal.getISBN());
                role = 3;
            }
            lblGreetAdmin.setVisible(false);
            tbLibs.setVisible(false);
            tbStudents.setVisible(false);
            tbBooks.setVisible(false);
        }
        else   if(tbLStudents!=null) {
            if (tbLStudents.isVisible()) {
                studentGlobal = (Students) tbLStudents.getSelectionModel().getSelectedItem();
                Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
                delete = con.prepareStatement("DELETE FROM Users WHERE UsersId=" + studentGlobal.getId());
                role = 2;
            } else if (tbLBooks.isVisible()) {
                bookGlobal = (Books) tbLBooks.getSelectionModel().getSelectedItem();
                Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
                delete = con.prepareStatement("DELETE FROM Books WHERE ISBN=" + bookGlobal.getISBN());
                role = 3;
            }
            lblGreetLibrarian.setVisible(false);
            tbLStudents.setVisible(false);
            tbLBooks.setVisible(false);
        }
        delete.execute();
        if(role != 0) {
            if(role == 1){
                Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
                get = con.prepareStatement("SELECT * FROM Users WHERE Role=1"); //updating librarians table
                getStmt = get.executeQuery();
                while (getStmt.next()) {
                    list1.add(new Librarian(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password")));
                }
                tbLibs.getItems().clear();
                for (Librarian u : list1) {
                    tbLibs.getItems().add(u);
                }
            }
            else if(role == 2) {
                Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
                Connection con1 = DriverManager.getConnection("jdbc:derby:./borrowed;", "user", "pass");
                PreparedStatement borrow= con1.prepareStatement("SELECT * FROM BORROWED_BOOKS where returned = false");
                get = con.prepareStatement("SELECT * FROM Users WHERE Role=2");
                ResultSet borrowStmt=borrow.executeQuery();
                getStmt = get.executeQuery();
                while (getStmt.next()) {
                    String taken="";
                    while (borrowStmt.next()){
                        if (borrowStmt.getString("userName").equals(getStmt.getString("FirstName")))
                            taken+=borrowStmt.getString("borrowedBookName");
                    }
                    list2.add(new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), taken));
                }
                tbStudents.getItems().clear();
                for (Students u : list2) {
                    tbStudents.getItems().add(u);
                }
            }
            else if(role == 3) {
                Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
                get = con.prepareStatement("SELECT * FROM BOOKS");//updating books table
                getStmt = get.executeQuery();
                while (getStmt.next()) {
                    list3.add(new Books(getStmt.getInt("ISBN"), getStmt.getString("title"), getStmt.getString("subject"), getStmt.getString("author"), getStmt.getString("publishDate")));
                }
                tbBooks.getItems().clear();
                for (Books u : list3) {
                    tbBooks.getItems().add(u);
                }
            }
            else{}
        }
    }

    ///librarian window methods
    @FXML
    public void onLStudents() throws Exception{
        tcIdL.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNameL.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastNameL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmailL.setCellValueFactory(new PropertyValueFactory<>("email"));
        ArrayList<Students> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        Connection con1 = DriverManager.getConnection("jdbc:derby:./borrowed;", "user", "pass");

        PreparedStatement borrow= con1.prepareStatement("SELECT * FROM BORROWED_BOOKS where returned = false");
        PreparedStatement get = con.prepareStatement("SELECT * FROM Users WHERE Role=2");
        ResultSet getStmt = get.executeQuery();
        ResultSet borrowStmt=borrow.executeQuery();
        if (!studentAlreadyExists) {
            while (getStmt.next()) {
                String taken="";
                while (borrowStmt.next()){
                    if (borrowStmt.getString("userName").equals(getStmt.getString("FirstName")))
                        taken+=borrowStmt.getString("borrowedBookName");
                }
                list.add(new Students(getStmt.getInt("UsersId"), getStmt.getString("FirstName"), getStmt.getString("LastName"), getStmt.getString("Email"), getStmt.getString("Login"), getStmt.getString("Password"), taken));
            }
            for (Students u : list) {
                tbLStudents.getItems().add(u);
            }
            studentAlreadyExists=true;
        }
        tbLStudents.setVisible(true);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        tbBorrowedBooks.setVisible(false);
        tbDeptor.setVisible(false);


    }

    @FXML
    public void onLBooks() throws Exception{
        tcISBNL.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tcTitleL.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthorL.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcSubjectL.setCellValueFactory(new PropertyValueFactory<>("subject"));
        tcPublishDateL.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        ArrayList<Books> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM BOOKS");
        ResultSet getStmt = get.executeQuery();
        if (!booksAlreadyExits) {
            while (getStmt.next()) {
                list.add(new Books(getStmt.getInt("ISBN"), getStmt.getString("title"), getStmt.getString("subject"), getStmt.getString("author"), getStmt.getString("publishDate")));
            }
            for (Books u : list) {
                tbLBooks.getItems().add(u);
            }
            booksAlreadyExits = true;
        }
        tbLStudents.setVisible(false);
        tbLBooks.setVisible(true);
        lblGreetLibrarian.setVisible(false);
        tbBorrowedBooks.setVisible(false);
        tbDeptor.setVisible(false);

    }
    @FXML
    public void onBorrowedBooks() throws Exception{
        tcBUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tcBISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tcBTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcTaken.setCellValueFactory(new PropertyValueFactory<>("taken"));
        tcReturn.setCellValueFactory(new PropertyValueFactory<>("returned"));
        tcBStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        ArrayList<Borrowed> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./borrowed;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM BORROWED_BOOKS");
        ResultSet getStmt = get.executeQuery();

        while (getStmt.next()) {

            list.add(new Borrowed(getStmt.getString("userName"), getStmt.getString("borrowedBookName"),String.valueOf( getStmt.getInt("borrowedBookIsbn")), getStmt.getString("takenDate"), getStmt.getString("returnDate"),getStmt.getBoolean("returned")));
        }
        for (Borrowed u : list) {
            tbBorrowedBooks.getItems().add(u);
        }

        tbBorrowedBooks.setVisible(true);
        tbLStudents.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        tbLBooks.setVisible(false);
        tbDeptor.setVisible(false);
    }
    //I can't do it
    @FXML
    public void onDeptor() throws Exception{
        tcDUsername.setCellValueFactory(new PropertyValueFactory<>("user"));
        tcDReturn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tcDTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        ArrayList<Borrowed> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./borrowed;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM BORROWED_BOOKS WHERE returned=false");
        ResultSet getStmt = get.executeQuery();

        while (getStmt.next()) {

            list.add(new Borrowed(getStmt.getString("userName"), getStmt.getString("borrowedBookName"),String.valueOf( getStmt.getInt("borrowedBookIsbn")), getStmt.getString("takenDate"), getStmt.getString("returnDate"),getStmt.getBoolean("returned")));
        }
        for (Borrowed u : list) {
            Date date =new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date parsingDate=ft.parse(u.getReturned());
            if (date.after(parsingDate))
                tbBorrowedBooks.getItems().add(u);
        }
        tbDeptor.setVisible(true);
        tbBorrowedBooks.setVisible(false);
        tbLBooks.setVisible(false);
        lblGreetLibrarian.setVisible(false);
        tbLStudents.setVisible(false);
    }
    ////////////////////////////////////
    public void onSDetails() {
        lblSid.setText(String.valueOf( studentGlobal.getId()));
        lblSName.setText(studentGlobal.getFirstName());
        lblSFamName.setText(studentGlobal.getLastName());
        lblSEmail.setText(studentGlobal.getEmail());

        lblGreetStudent.setVisible(false);
        lblSName.setVisible(true);
        lblSFamName.setVisible(true);
        lblSid.setVisible(true);
        lblSEmail.setVisible(true);
        // listsBooks.setVisible(true);
        // lblCurFine.setVisible(true);
        // lblSBorrowed.setVisible(true);
        tbSbooks.setVisible(false);
        btnSReserve.setVisible(false);
    }


    @FXML
    public void onSBooks() throws Exception {
        tcSISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tcSTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcSAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcSSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        tcSPublishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        ArrayList<Books> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:derby:./books;", "user", "pass");
        PreparedStatement get = con.prepareStatement("SELECT * FROM BOOKS");
        ResultSet getStmt = get.executeQuery();
        if (!booksAlreadyExits) {
            while (getStmt.next()) {
                list.add(new Books(getStmt.getInt("ISBN"), getStmt.getString("title"), getStmt.getString("subject"), getStmt.getString("author"), getStmt.getString("publishDate")));
            }
            for (Books u : list) {
                tbSbooks.getItems().add(u);
            }
            booksAlreadyExits = true;
        }
        lblGreetStudent.setVisible(false);
        lblSName.setVisible(false);
        lblSFamName.setVisible(false);
        lblSid.setVisible(false);
        lblSEmail.setVisible(false);
        listsBooks.setVisible(false);
        lblCurFine.setVisible(false);
        lblSBorrowed.setVisible(false);
        tbSbooks.setVisible(true);
        btnSReserve.setVisible(true);
    }


    //Main Add Button
    @FXML
    private void onAdd(ActionEvent event) throws Exception {
        bookGlobal=null;
        studentGlobal=null;
        listsBooks=null;
        borrowedGlobal=null;
        if(tbStudents!=null) {
            if (tbStudents.isVisible() || tbLibs.isVisible()) {   //if Students table opened = Student Add List opened
                openPage(event, "AddUsers");
                role = 0;
                mode = 0;
            } else if (tbBooks.isVisible()) {  //if Books table opened = Books Add List opened
                openPage(event, "AddBooks");
                mode = 1;

            }
        }
        else  if(tbLStudents!=null) {
            if (tbLStudents.isVisible()) {   //if Students table opened = Student Add List opened
                openPage(event, "AddUsers");
                role = 1;
                mode = 0;
            } else if (tbLBooks.isVisible()) {  //if Books table opened = Books Add List opened
                openPage(event, "AddBooks");
                mode = 1;

            }
            else if(tbBorrowedBooks.isVisible()){
                openPage(event, "AddBorrowedBooks");
                mode=2;
            }
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
