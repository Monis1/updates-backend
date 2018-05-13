package com.meezotech.updatesbackend.notifications.payloads;

public class PostNotificationPayload {

    private long groupId;
    private long userId;
    private String groupName;
    private String text;

    public PostNotificationPayload(long groupId, long userId, String groupName, String text) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
        this.text = text;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
