package dao;

import model.TodoTableData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDAO {

    public static void createTodo(String particulars, String notes, Date reminder, Date pendingFrom) {
        java.sql.Date reminderDateTime = new java.sql.Date(reminder.getTime());
        java.sql.Date pendingFromDateTime = new java.sql.Date(pendingFrom.getTime());
        try {
            DBUtil.dbConnect();
            Connection connection = DBUtil.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TODO(particulars,notes,reminder,pending_from,cleared)" +
                    " VALUES (?,?,?,?,?)");
            pstmt.setString(1,particulars);
            pstmt.setString(2,notes);
            pstmt.setDate(3,reminderDateTime);
            pstmt.setDate(4,pendingFromDateTime);
            pstmt.setInt(5,0);
            pstmt.executeUpdate();
            DBUtil.dbDisconnect();
            System.out.println("SUCCESS: Insert into TODO");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR: Insert into Todo failed");
        }
    }

    public static List<TodoTableData> getAllTodos(){
        ResultSet todoList=null;
        try {
            DBUtil.dbConnect();
            todoList = DBUtil.executeRead("SELECT * FROM TODO;");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<TodoTableData> todoTableList = new ArrayList<>();
        try {
        while (todoList.next()){
                TodoTableData todo = new TodoTableData();
                todo.setSlNo(todoList.getInt(1));
                todo.setParticulars(todoList.getString(2));
                todo.setNotes(todoList.getString(3));
                todo.setReminder(todoList.getTimestamp(4));
                todo.setPendingFrom(todoList.getTimestamp(5));
                todo.setCleared(todoList.getBoolean(6));
            todoTableList.add(todo);
        }
            DBUtil.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoTableList;
    }
    public static void updateTodo(String columnName,String value,int slNo){
        try {
            DBUtil.dbConnect();
            Connection connection = DBUtil.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE TODO SET "+columnName+" = ? WHERE slNo = ? ");
            pstmt.setString(1,value);
            pstmt.setInt(2,slNo);
            pstmt.executeUpdate();
            DBUtil.dbDisconnect();
            System.out.println("SUCCESS: Update TODO");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR: Update Todo failed");
        }
    }
}
