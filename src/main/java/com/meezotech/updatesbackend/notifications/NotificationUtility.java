package com.meezotech.updatesbackend.notifications;

import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.notifications.payloads.PostNotificationPayload;
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
        body.put("to", "/topics/test");
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "Update Notification");
        notification.put("body", getPostNotificationText(post));

        PostNotificationPayload postNotificationPayload =
                new PostNotificationPayload(post.getGroup().getId(), post.getUser().getId(),
                        post.getGroup().getName(), getPostNotificationText(post));

        JSONObject data = new JSONObject(postNotificationPayload);

        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            System.out.println(firebaseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getPostNotificationText(Post post){
       return String.format("%s %s posted in %s", post.getUser().getFirstName()
        ,post.getUser().getLastName(), post.getGroup().getName());
    }

}
