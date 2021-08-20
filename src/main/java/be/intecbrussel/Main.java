package be.intecbrussel;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;


public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.print("User Id: ");
        String userId = scanner.nextLine();

        try {
            if (new IdManager().checkId(userId)) {
                showMenu(userId);

            } else {
                System.out.print("First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Last Name: ");
                String lastName = scanner.nextLine();

                String query = "INSERT INTO user_mustafa (id, firstName, lastName) VALUES (?,?,?)";
                try {
                    PreparedStatement pt = JDBCManager.getConnection().prepareStatement(query);
                    pt.setString(1, userId);
                    pt.setString(2, firstName);
                    pt.setString(3, lastName);
                    pt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                showMenu(userId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showMenu(String userId) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("*************");
            System.out.println("1.Show List");
            System.out.println("2.Add to your list");
            System.out.println("3.Update your list");
            System.out.println("4.Delete from your list");
            System.out.println("5.Exit");
            System.out.print("Please make your choose (1-5) : ");
            int choose = scan.nextInt();
            System.out.println("*************");
            if (choose == 1) UserService.showList(userId);
            if (choose == 2) UserService.addTask(userId);
            if (choose == 3) UserService.updateTask(userId);
            if (choose == 4) UserService.deleteTask(userId);
            if (choose == 5) {
                try {
                    JDBCManager.getConnection().close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
        }
    }
}
