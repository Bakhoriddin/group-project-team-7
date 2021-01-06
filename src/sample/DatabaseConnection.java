package sample;

import java.sql.*;

public class DatabaseConnection {
    private static Connection con;

    public DatabaseConnection() throws SQLException {
        con=DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");;
    }

    public static ResultSet getStmt(String table) throws SQLException {
        PreparedStatement getPStmt= con.prepareStatement("SELECT * FROM "+table);
        ResultSet get=getPStmt.executeQuery() ;
        return get;
    }
}
