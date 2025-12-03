package studentApplication.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import studentApplication.classes.Message;

public class MessageDAO {
    private final Connection conn;

    public MessageDAO(Connection conn) {
        this.conn = conn;   
    }

    // INSERT
    public boolean insertMessage(Message message) {
        String sql = "INSERT INTO Messages (message, time_sent, sender_id, receiver_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, message.getMessage());
            ps.setTimestamp(2, message.getTimeSent());
            ps.setInt(3, message.getSenderId());
            ps.setInt(4, message.getReceiverId());
            

            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (Exception e) {
            System.out.println("Error inserting Message: " + e.getMessage());
            return false;
        }
    }

    // GET ALL MESSAGES BETWEEN TWO USERS
    public List<String> getConversation(int userA, int userB) {
        List<String> list = new ArrayList<>();

        String sql = """
            SELECT message, time_sent, sender_id 
            FROM Messages 
            WHERE (sender_id = ? AND receiver_id = ?) 
               OR (sender_id = ? AND receiver_id = ?)
            ORDER BY time_sent ASC
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userA);
            ps.setInt(2, userB);
            ps.setInt(3, userB);
            ps.setInt(4, userA);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String from = rs.getInt("sender_id") == userA ? "You" : "Them";
                list.add(from + ": " + rs.getString("message"));
            }

        } catch (Exception e) {
            System.out.println("Error getting conversation: " + e.getMessage());
        }

        return list;
    }

    // DELETE ALL MESSAGES BETWEEN TWO USERS
    public void deleteConversation(int userA, int userB) {
        String sql = """
            DELETE FROM Messages 
            WHERE (sender_id = ? AND receiver_id = ?) 
               OR (sender_id = ? AND receiver_id = ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userA);
            ps.setInt(2, userB);
            ps.setInt(3, userB);
            ps.setInt(4, userA);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error deleting conversation: " + e.getMessage());
        }
    }
}

