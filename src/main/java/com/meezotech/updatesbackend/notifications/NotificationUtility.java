package com.meezotech.updatesbackend.notifications;

import com.meezotech.updatesbackend.domain.Comment;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import com.meezotech.updatesbackend.notifications.payloads.PostReactionsNotificationPayload;
import com.meezotech.updatesbackend.notifications.payloads.PostNotificationPayload;
import com.meezotech.updatesbackend.utilities.Constants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class NotificationUtility {

    @Autowired
    PushNotificationService pushNotificationService;

    public void sendPostNotification(Post post) {
        JSONObject body = new JSONObject();
        JSONObject notification = new JSONObject();
        notification.put("title", "Update Notification");
        notification.put("body", getPostNotificationText(post));
        notification.put("click_action", "com.abdulahad.halatupdatesapp_TARGET_NOTIFICATION"); // default filter for mobile
        PostNotificationPayload postNotificationPayload =
                new PostNotificationPayload(post.getId(), post.getGroup().getId(), post.getUser().getId(),
                        post.getGroup().getName(), getPostNotificationText(post));
        JSONObject data = new JSONObject(postNotificationPayload);
        data.put("type", Constants.POST_NOTIFICATION_TYPE);
        body.put("notification", notification);
        body.put("data", data);
        sendNotification(body);
    }

    private String getPostNotificationText(Post post){
       return String.format("%s %s posted in %s", post.getUser().getFirstName()
        ,post.getUser().getLastName(), post.getGroup().getName());
    }

    private void sendNotification(JSONObject notificationBody){
        notificationBody.put("to", "/topics/test"); // by default all subscribed to topic test
        notificationBody.put("priority", "high"); // by default all notifications priority is high
        HttpEntity<String> request = new HttpEntity<>(notificationBody.toString());
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            System.out.println(firebaseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommentNotification(Comment comment, long userId) {
        JSONObject body = new JSONObject();
        JSONObject notification = new JSONObject();
        notification.put("title", "Comment Notification");
        notification.put("body", getCommentNotificationText(comment));
        notification.put("click_action", "com.abdulahad.halatupdatesapp_TARGET_NOTIFICATION"); // default filter for mobile
        PostReactionsNotificationPayload commentNotificationPayload =
                new PostReactionsNotificationPayload(comment.getPost().getId(),
                        comment.getUser().getId(), userId, getCommentNotificationText(comment));
        JSONObject data = new JSONObject(commentNotificationPayload);
        data.put("type", Constants.COMMENT_NOTIFICTION_TYPE);
        body.put("notification", notification);
        body.put("data", data);
        sendNotification(body);
    }

    private String getCommentNotificationText(Comment comment) {
        return String.format("%s %s commented on your post", comment.getUser().getFirstName()
                ,comment.getUser().getLastName());
    }

    public void sendReactionNotification(Reaction reaction, long userId) {
        JSONObject body = new JSONObject();
        JSONObject notification = new JSONObject();
        notification.put("title", "Like Notification");
        notification.put("body", getLikeNotificationText(reaction));
        notification.put("click_action", "com.abdulahad.halatupdatesapp_TARGET_NOTIFICATION"); // default filter for mobile
        PostReactionsNotificationPayload commentNotificationPayload =
                new PostReactionsNotificationPayload(reaction.getPost().getId(),
                        reaction.getUser().getId(), userId, getLikeNotificationText(reaction));
        JSONObject data = new JSONObject(commentNotificationPayload);
        data.put("type", Constants.REACTION_NOTIFICATION_TYPE);
        body.put("notification", notification);
        body.put("data", data);
        sendNotification(body);
    }

    private String getLikeNotificationText(Reaction reaction) {
        return String.format("%s %s likes your post", reaction.getUser().getFirstName()
                ,reaction.getUser().getLastName());
    }


}
