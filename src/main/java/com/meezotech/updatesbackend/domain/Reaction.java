package com.meezotech.updatesbackend.domain;

import javax.persistence.*;

@Entity
public class Reaction {

    @Id
    private Long userId;

    @Id
    private Long postId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    private String reacionType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

