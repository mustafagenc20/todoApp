package be.intecbrussel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCManager {
    private static String jdbcURL = "jdbc:mysql://moktok.intecbrussel.org:33306/sakila";
    private static String jdbcUserName = "sakila";
    private static String jdbcPassword = "VerySecurePassword";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
//            System.out.println("Connection succeed");
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to connect to the database....");
            e.printStackTrace();
        }
        return connection;
    }
}
