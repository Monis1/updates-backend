package com.meezotech.updatesbackend.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentText(String value) {
        this.commentText = value;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public User getUser() {
        return user;
    }

    public void setPost(Post value) {
        this.post = value;
    }

    public Post getPost() {
        return post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

