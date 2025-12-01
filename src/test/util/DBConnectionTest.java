package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnectionTest {
    
    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
    } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
    }
    return DriverManager.getConnection(DB_URL, USER, PASS);
}

}