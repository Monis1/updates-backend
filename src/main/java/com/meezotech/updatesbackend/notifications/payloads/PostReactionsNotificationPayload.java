package com.meezotech.updatesbackend.notifications.payloads;

public class PostReactionsNotificationPayload {

    private long postId;
    private long userId;
    private String text;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public PostReactionsNotificationPayload(long postId, long userId, String text) {
        this.postId = postId;
        this.userId = userId;
        this.text = text;
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

}
