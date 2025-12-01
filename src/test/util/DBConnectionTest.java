package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnectionTest {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testkeyboardbuilderdb";
    private static final String USER = "root";
    private static final String PASS = "Loanvu2977!";

    public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
    } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
    }
    return DriverManager.getConnection(DB_URL, USER, PASS);
}

}