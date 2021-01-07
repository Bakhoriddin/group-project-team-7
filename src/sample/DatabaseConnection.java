package sample;

import java.sql.*;

public class DatabaseConnection {
    private static Connection con;

    public DatabaseConnection() throws SQLException {
            }

    public static Connection getCon() throws SQLException {
        con=DriverManager.getConnection("jdbc:derby:./db;", "user", "pass");
        return con;
    }

    public static ResultSet getStmt(String table) throws SQLException {
        PreparedStatement getStmt= getCon().prepareStatement("SELECT * FROM "+table);
        ResultSet get=getStmt.executeQuery() ;
        con.close();

        return get;
    }
    public static  ResultSet searchStmt (String table, String where, String what)throws Exception{
        PreparedStatement getStmt= getCon().prepareStatement("SELECT * FROM "+table+" WHERE "+where+" = '"+what+"'");
        ResultSet get=getStmt.executeQuery() ;
        con.close();
        return get;
    }
    public static  ResultSet searchStmt (String table, String where, int what)throws Exception{
        PreparedStatement getStmt= getCon().prepareStatement("SELECT * FROM "+table+" WHERE "+where+" = "+what+"");
        ResultSet get=getStmt.executeQuery() ;
        con.close();
        return get;
    }
    public static  boolean deleteStmt(String table, String where, String what) throws  Exception{
        PreparedStatement stmt= getCon().prepareStatement("DELETE FROM "+table+" WHERE "+where+" = '"+what+"'");
        boolean deleted= stmt.execute();
        con.close();
        return  deleted;
    }
    public static  boolean deleteStmt(String table, int id) throws  Exception{
        boolean deleted= false;
        if(table.equals("Users")){
            PreparedStatement stmt= getCon().prepareStatement("DELETE FROM Users WHERE UsersId = "+id+"");
             deleted= stmt.execute();
        }
        con.close();
        return  deleted;
    }



}
