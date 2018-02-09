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

        Group group1 = new Group();
        group1.setName("Updates1");
        groupRepository.save(group1);

        User user = new User();
        user.setFirstName("Moid");
        user.setLastName("khan");
        user.setProfilePictureUrl("https://scontent.fkhi2-1.fna.fbcdn.net/v/t1.0-9/22141210_1527317537304126_6355389030864021810_n.jpg?oh=56c4f6a71f3a08748ca30eda46605c47&oe=5ADE1571");
        user.setEmail("abdulmoeedkhan92@gmail.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Monis");
        user1.setLastName("khan");
        user1.setProfilePictureUrl("https://scontent.fkhi2-1.fna.fbcdn.net/v/t1.0-9/23722309_1680089072035219_5259122116517785807_n.jpg?oh=51d7162810a0c5093bfb04128a2ebb5e&oe=5B262F0F");
        user1.setEmail("monisahmed8@gmail.com");
        user1.setGender(Gender.MALE);
        userRepository.save(user1);

        for (int i = 0; i < size; i++) {
            Post post = new Post();
            post.setText("Update " + i);
            post.setDate(new Date());

            Media media1 = new Media();
            media1.setMediaType(MediaType.VIDEO);
            media1.setThumbnailUrl("https://imgc.allpostersimages.com/img/print/posters/toy-story-woody-buzz_a-G-13390942-0.jpg");
            media1.setName("Toy Story");
            media1.setUrl("http://www.html5videoplayer.net/videos/toystory.mp4");
            media1.setPost(post);

            Media media2 = new Media();
            media2.setMediaType(MediaType.IMAGE);
            media2.setName("Just Pakistani Things");
            media2.setUrl("https://scontent.fkhi2-1.fna.fbcdn.net/v/t1.0-9/27331650_1003078486515614_2119376052328880588_n.jpg?oh=92f82259b50a871aa8f7ef60187e22f1&oe=5AE153AB");
            media2.setPost(post);

            post.getMedia().add(media1);
            post.getMedia().add(media2);

            if(i%2==0) {
                post.setGroup(group);
                post.setUser(user);
            }
            else {
                post.setGroup(group1);
                post.setUser(user1);
            }

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
        media1.setThumbnailUrl("www.hy.com/stbn.jpg");
        media1.setName("s");
        media1.setUrl("www.hy.com/s.mp4");
        media1.setPost(post1);

        post1.getMedia().add(media1);

        Post post2 = new Post();
        post2.setText("see this non sense");

        Media media2 = new Media();
        media2.setName("x");
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
