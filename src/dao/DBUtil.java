package dao;

import java.sql.*;

public class DBUtil {

    private static final String DB_URL =
            "jdbc:h2:~/test";
    private static final String JDBC_DRIVER =
            "org.h2.Driver";
    private static final String USER = "prajwalatm";
    private static final String PASSWORD = "chinnari123";

    static Connection getConnection() {
        return connection;
    }

    static void setConnection(Connection connection) {
        DBUtil.connection = connection;
    }

    private static Connection connection = null;

    static void dbConnect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("JDBC Driver registered successfully");
        }
        catch(ClassNotFoundException e){
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
            throw e;
        }
        try{
            if(connection==null || connection.isClosed())
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }
        catch(SQLException e){
            System.out.println("Connection to the DB failed!");
            e.printStackTrace();
            throw e;
        }
    }

    static void dbDisconnect() throws SQLException{
        try {
            if(connection!=null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    static ResultSet executeRead(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            //Connect to DB
           // dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = connection.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        }
        return resultSet;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    static void executeWrite(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB
            dbConnect();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
