package entities;

import java.util.List;

public class Post {
    public String id;
    public String title;
    public String body;
    public String author;
    public String email;
    public List<Comment> comments;

    public Post(String id, String title, String body, String author, String email, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.email = email;
        this.comments = comments;
    }

    public Post(String id, String title, String body, String author, String email) {
        this.id = id;
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
