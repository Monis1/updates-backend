package com.meezotech.updatesbackend.notifications;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PushNotificationService {

    private static final String FIREBASE_SERVER_KEY = "AAAArABUBDo:APA91bGLPgjVg3LU2OcgL4t-aLq7O4Vy0KFuvKNNxl-TYZM5fC5o6mJpHc9J7Y9yj_0odmOvNGvt_XY_QhD246zeLsmsxqP3YIEKhxc6IT88RnJYCp-bVqF0fpWT8liQxLzEo4nREat-";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);

    }

}
