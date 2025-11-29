package studentApplication;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class UserDAO {

    // Check if email already exists
    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM Users WHERE email = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            System.out.println("Error checking email: " + e.getMessage());
            return true; // safer to block registration if error
        }
    }

    // Register a new user
    public boolean registerUser(User user) {

        if (emailExists(user.getEmail())) {
            System.out.println("Email already taken.");
            return false;
        }

        String sql = "INSERT INTO Users (first_name, last_name, email, username, password, major, school_year) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getMajor());
            stmt.setInt(7, user.getSchoolYear());

            int rows = stmt.executeUpdate();
            return rows == 1;

        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
}
