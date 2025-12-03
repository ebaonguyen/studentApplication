    package studentApplication.classes;

import java.sql.Date;
    

public class Post {
    private int postId;
    private String description;
    private Date datePosted;
    private String className;
    private boolean status;
    private int numberOfComments;
    private int userId;
    private String username;
    
    public Post() {}

    public Post(String description, Date datePosted, String className, boolean status, int numberOfComments, int userId) {
        this.description = description;
        this.datePosted = datePosted;
        this.className = className;
        this.status = status;
        this.numberOfComments = numberOfComments;
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

}
