package studentApplication.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import studentApplication.classes.Post;
import studentApplication.classes.User;
import util.DBConnection;

public class PostDAO {
    private final Connection conn;

    public PostDAO(Connection conn) {
        this.conn = conn;   
    }
    // Check if email already exists
    public boolean createPost(Post post, User user) {

        String sql = "INSERT INTO Posts (description, date_posted, class, status, number_of_comments, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, post.getDescription());
            stmt.setDate(2, post.getDatePosted());
            stmt.setString(3, post.getClassName());
            stmt.setBoolean(4, post.getStatus());
            stmt.setInt(5, post.getNumberOfComments());
            stmt.setInt(6, user.getUserId());

            
            int rows = stmt.executeUpdate();

            if (rows == 1) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                    if(generatedKeys.next()) {
                        post.setPostId(generatedKeys.getInt(1));
                    }
                } catch (SQLException e) { 
                    System.out.println("Error retrieving post ID: " + e.getMessage());
                }
            }
            return rows == 1;

        } catch (Exception e) {
            System.out.println("Error registering post: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePostsFromUserId(int userId) {
        String sql = "DELETE FROM Posts WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error clearing users: " + e.getMessage());
            return false;
        }
    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT p.*, u.username FROM Posts p JOIN Users u ON p.user_id = u.user_id";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(rs.getString("description"),
                                  rs.getDate("date_posted"),
                                  rs.getString("class"),
                                  rs.getBoolean("status"),
                                  rs.getInt("number_of_comments"),
                                  rs.getInt("user_id"));
                post.setPostId(rs.getInt("post_id"));
                post.setUsername(rs.getString("username"));
                list.add(post);
                      
            }

        } catch (Exception e) {
            System.out.println("Error getting Acceptances: " + e.getMessage());
        }

        return list;
    }

    public List<Post> getAllPostsFromUser(int userId) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT p.*, u.username FROM Posts p JOIN Users u ON p.user_id = u.user_id WHERE p.user_id = ?" ;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(rs.getString("description"),
                                  rs.getDate("date_posted"),
                                  rs.getString("class"),
                                  rs.getBoolean("status"),
                                  rs.getInt("number_of_comments"),
                                  rs.getInt("user_id"));
                post.setPostId(rs.getInt("post_id"));
                post.setUsername(rs.getString("username"));
                list.add(post);
                      
            }

        } catch (Exception e) {
            System.out.println("Error getting Acceptances: " + e.getMessage());
        }

        return list;
    }

    public boolean exists(int postId) {
        String sql = "SELECT * FROM Posts WHERE post_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Error checking Acceptance: " + e.getMessage());
        }
        return false;
    }

    public void editPost(Post post, Post editedPost, User user ) {
            String sql = "UPDATE Posts SET description = ?, date_posted = ?, class = ?, status = ?, number_of_comments = ?, user_id = ? WHERE user_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, post.getDescription());
            stmt.setDate(2, post.getDatePosted());
            stmt.setString(3, post.getClassName());
            stmt.setBoolean(4, post.getStatus());
            stmt.setInt(5, post.getNumberOfComments());
            stmt.setInt(6, user.getUserId());

                stmt.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error editing user: " + e.getMessage());
            }
        }
    
    
}
