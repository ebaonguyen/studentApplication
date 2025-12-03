package studentApplication.daos;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcceptanceDAO {
    private final Connection conn;

    public AcceptanceDAO(Connection conn) {
        this.conn = conn;   
    }
    // INSERT
    public boolean insertAcceptance(Date dateAccepted, int userId, int postId) {
        String sql = "INSERT INTO Acceptance (date_accepted, user_id, post_id) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, dateAccepted);
            ps.setInt(2, userId);
            ps.setInt(3, postId);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (Exception e) {
            System.out.println("Error inserting Acceptance: " + e.getMessage());
            return false;
        }
    }

    // GET one record
    public boolean exists(int userId, int postId) {
        String sql = "SELECT * FROM Acceptance WHERE user_id = ? AND post_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Error checking Acceptance: " + e.getMessage());
        }
        return false;
    }

    // DELETE
    public void deleteAcceptance(int userId, int postId) {
        String sql = "DELETE FROM Acceptance WHERE user_id = ? AND post_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error deleting Acceptance: " + e.getMessage());
        }
    }

    // GET ALL
    public List<String> getAllAcceptance() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Acceptance";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add("User " + rs.getInt("user_id") +
                         " accepted Post " + rs.getInt("post_id") +
                         " on " + rs.getDate("date_accepted"));
            }

        } catch (Exception e) {
            System.out.println("Error getting Acceptances: " + e.getMessage());
        }

        return list;
    }
}



