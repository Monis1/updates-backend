package com.meezotech.updatesbackend.domain;

import javax.persistence.*;

@Entity
public class Reaction {

    @Id
    private long userId;

    @Id
    private long postId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    private String reacionType;

    private void setUserId(long value) {
        this.userId = value;
    }

    public long getUserId() {
        return userId;
    }

    private void setPostId(long value) {
        this.postId = value;
    }

    public long getPostId() {
        return postId;
    }

    public void setReacionType(String value) {
        this.reacionType = value;
    }

    public String getReacionType() {
        return reacionType;
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

}

