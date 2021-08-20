package be.intecbrussel;

import java.sql.*;
import java.util.Scanner;


public class UserService {
    public static void showList(String userId) {
        try {
            Statement statement = JDBCManager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from todo_mustafa WHERE user_id = " + userId);
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addTask(String userId) {

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Scanner scan= new Scanner(System.in);
        System.out.print("Add new task :");
        String description = scan.nextLine();

        String query = "INSERT INTO todo_mustafa (description, user_id, date_created, finished) VALUES (?,?,?,?)";
        try {
            PreparedStatement pt = JDBCManager.getConnection().prepareStatement(query);
            pt.setString(1, description);
            pt.setString(2, userId);
            pt.setDate(3, sqlDate);
            pt.setString(4,"0");
            pt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteTask(String userId) {

        Scanner scan= new Scanner(System.in);
        System.out.print("Delete task Id:");
        String taskId = scan.nextLine();

        String query = String.format("DELETE FROM todo_mustafa WHERE id =" + taskId);
        try {
            Statement st= JDBCManager.getConnection().createStatement();
            int deleted = st.executeUpdate(query);
            System.out.println("Deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateTask(String userId) {

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Scanner scan= new Scanner(System.in);
        System.out.print("Task id:");
        String taskId = scan.nextLine();
        System.out.print("Update description :");
        String description = scan.nextLine();
        System.out.print("Finished task? :");
        String finished = scan.nextLine();


        String query =String.format("UPDATE todo_mustafa SET description='%s' , finished='%s' WHERE id = " + taskId,description,finished);
        try {
            Statement st = JDBCManager.getConnection().createStatement();
            int update = st.executeUpdate(query);
            System.out.println("Task id updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
