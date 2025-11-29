package org.cs157.studentApplication;

import java.sql.Connection;
import java.sql.Statement;

import org.cs157.util.DBConnection;

public class CreateCommentsTable {
	  public static void main(String[] args) {
	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement()) {

	            String sql = """
	                CREATE TABLE IF NOT EXISTS Comments (
	                    comment_id INT AUTO_INCREMENT PRIMARY KEY,
	                    comment VARCHAR(300),
	                    date_commented DATE,
	                    user_id INT NOT NULL, 
	                    post_id INT NOT NULL,
	                    FOREIGN KEY (user_id) REFERENCES Users(user_id),
	                    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
	                );
	            """;

	            stmt.execute(sql);
	            System.out.println("Comments table created.");

	        } catch (Exception e) {
	            System.out.println("Error creating Comments table: " + e.getMessage());
	        }
	    }
}
