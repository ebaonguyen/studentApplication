package studentApplication.classes;

import java.sql.Date;

public class Comment {
    private int commentId;
    private String commentText;
    private int postId;
    private int userId;
    private Date dateCommented;
    
    public Comment() {}
    public Comment(String commentText, Date dateCommented, int postId, int userId) {
        this.commentText = commentText;
        this.dateCommented = dateCommented;
        this.postId = postId;
        this.userId = userId;
    }
    public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getDateCommented() {
        return dateCommented;
    }
    public void setDateCommented(Date dateCommented) {
        this.dateCommented = dateCommented;
    }
}
