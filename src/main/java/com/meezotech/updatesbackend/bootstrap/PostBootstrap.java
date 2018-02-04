package com.meezotech.updatesbackend.bootstrap;

import com.meezotech.updatesbackend.domain.*;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private GroupRepository groupRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostBootstrap(GroupRepository groupRepository, PostRepository postRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        postRepository.save(getBulkPosts(100));
    }

    private List<Post> getBulkPosts(int size) {

        List<Post> posts = new ArrayList<>();

        Group group = new Group();
        group.setName("Updates");
        groupRepository.save(group);

        for (int i = 0; i < size; i++) {
            Post post = new Post();
            post.setText("Update " + i);
            post.setDate(new Date());

            Media media1 = new Media();
            media1.setMediaType(MediaType.VIDEO);
            media1.setUrl("www.hy.com/video" + i);
            media1.setPost(post);

            Media media2 = new Media();
            media2.setMediaType(MediaType.IMAGE);
            media2.setUrl("www.hy.com/image" + i);
            media2.setPost(post);

            post.getMedia().add(media1);
            post.getMedia().add(media2);

            post.setGroup(group);

            User user = new User();
            user.setFirstName("Person " + i);
            user.setGender(Gender.MALE);

            userRepository.save(user);

            post.setUser(user);

            posts.add(post);
        }
        return posts;
    }

    private List<Post> getPosts() {

        List<Post> posts = new ArrayList<>(2);

        Group group1 = new Group();
        group1.setName("PK Updates");

        groupRepository.save(group1);

        Post post1 = new Post();
        post1.setText("Too much traffic today in khi");

        Media media1 = new Media();
        media1.setMediaType(MediaType.VIDEO);
        media1.setUrl("www.hy.com/s.mp4");
        media1.setPost(post1);

        post1.getMedia().add(media1);

        Post post2 = new Post();
        post2.setText("see this non sense");

        Media media2 = new Media();
        media2.setMediaType(MediaType.IMAGE);
        media2.setUrl("www.hy.com/x.jpg");
        media2.setPost(post2);

        post2.getMedia().add(media2);

        Group group2 = new Group();
        group2.setName("Weather Updates");

        groupRepository.save(group2);

        post1.setGroup(group1);
        post2.setGroup(group2);

        posts.add(post1);
        posts.add(post2);

        return posts;
    }
}
