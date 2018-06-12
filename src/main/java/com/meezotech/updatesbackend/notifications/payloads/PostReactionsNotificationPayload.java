package com.meezotech.updatesbackend.notifications.payloads;

public class PostReactionsNotificationPayload {

    private long postId;
    private long currentUserId;
    private long userId;
    private String text;

    public PostReactionsNotificationPayload(long postId, long currentUserId, long userId, String text) {
        this.postId = postId;
        this.currentUserId = currentUserId;
        this.userId = userId;
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(long currentUserId) {
        this.currentUserId = currentUserId;
    }

}
