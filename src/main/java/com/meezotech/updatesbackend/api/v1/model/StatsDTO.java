package com.meezotech.updatesbackend.api.v1.model;

public class StatsDTO {

    private long numberOfReactions;
    private long numberOfComments;
    private long numberOfPosts;
    private long numberOfUsers;

    public StatsDTO() {
        this.numberOfReactions = 0;
        this.numberOfComments = 0;
        this.numberOfPosts = 0;
        this.numberOfUsers = 0;
    }

    public long getNumberOfReactions() {
        return numberOfReactions;
    }

    public void setNumberOfReactions(long numberOfReactions) {
        this.numberOfReactions = numberOfReactions;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public long getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
}
