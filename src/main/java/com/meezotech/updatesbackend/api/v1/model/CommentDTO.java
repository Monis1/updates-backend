package com.meezotech.updatesbackend.api.v1.model;

import java.util.Date;

public class CommentDTO {

    private UserDTO user;
    private Long postId;
    private String commentText;
    private Date date;

    public CommentDTO() {
    }

    public CommentDTO(UserDTO user, Long postId, String commentText, Date date) {
        this.user = user;
        this.postId = postId;
        this.commentText = commentText;
        this.date = date;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
