package entities;

public class Comment {
    public String id;
    public String comment;
    public String commenter;
    public String email;

    public Comment(String id, String comment, String commenter, String email) {
        this.id = id;
        this.comment = comment;
        this.commenter = commenter;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
