package studentApplication.daos;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final Connection conn;

    public CommentDAO(Connection conn) {
        this.conn = conn;   
    }

    // INSERT
    public void insertComment(String comment, Date dateCommented, int userId, int postId) {
        String sql = "INSERT INTO Comments (comment, date_commented, user_id, post_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, comment);
            ps.setDate(2, dateCommented);
            ps.setInt(3, userId);
            ps.setInt(4, postId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error inserting Comment: " + e.getMessage());
        }
    }

    // GET BY ID
    public String getCommentById(int id) {
        String sql = "SELECT * FROM Comments WHERE comment_id = ?";
        String result = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString("comment");
            }

        } catch (Exception e) {
            System.out.println("Error getting Comment: " + e.getMessage());
        }

        return result;
    }

    // GET ALL COMMENTS FOR A POST
    public List<String> getCommentsForPost(int postId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT comment FROM Comments WHERE post_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("comment"));
            }

        } catch (Exception e) {
            System.out.println("Error getting comments for post: " + e.getMessage());
        }

        return list;
    }

    // DELETE
    public void deleteComment(int id) {
        String sql = "DELETE FROM Comments WHERE comment_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error deleting Comment: " + e.getMessage());
        }
    }
}

