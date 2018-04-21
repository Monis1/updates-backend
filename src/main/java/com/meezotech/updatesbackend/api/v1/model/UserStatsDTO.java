package com.meezotech.updatesbackend.api.v1.model;

public class UserStatsDTO {

    private Long numberOfReactions;
    private Long numberOfComments;
    private Long numberOfPosts;

    public UserStatsDTO() {
        this.numberOfReactions = 0L;
        this.numberOfComments = 0L;
        this.numberOfPosts = 0L;
    }

    public Long getNumberOfReactions() {
        return numberOfReactions;
    }

    public void setNumberOfReactions(Long numberOfReactions) {
        this.numberOfReactions = numberOfReactions;
    }

    public Long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
