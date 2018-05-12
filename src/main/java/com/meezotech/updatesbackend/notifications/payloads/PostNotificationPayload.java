package com.meezotech.updatesbackend.notifications.payloads;

public class PostNotificationPayload {

    private long groupId;
    private String text;

    public PostNotificationPayload(long groupId, String text) {
        this.groupId = groupId;
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
}
