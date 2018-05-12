package com.meezotech.updatesbackend.notifications;

import com.fasterxml.jackson.databind.util.JSONPObject;

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

        JSONObject data = new JSONObject();
        data.put("groupId", post.getGroup().getId());

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
