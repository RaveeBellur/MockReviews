package entities;

import java.util.List;

public class Review {
    private String id;
    private String title;
    private String body;
    private String author;
    private String email;
    private List<Comment> comments;

    public Review() {

    }

    public Review(String title, String body, String author, String email, List<Comment> comments) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.email = email;
        this.comments = comments;
    }

    public Review(String title, String body, String author, String email) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
