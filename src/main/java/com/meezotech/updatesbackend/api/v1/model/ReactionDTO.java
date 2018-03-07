package com.meezotech.updatesbackend.api.v1.model;

public class ReactionDTO {

    private Long userId;
    private Long postId;
    private String reactionType;

    public ReactionDTO() {
    }

    public ReactionDTO(Long userId, Long postId, String reactionType) {
        this.userId = userId;
        this.postId = postId;
        this.reactionType = reactionType;
    }

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

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }
}
