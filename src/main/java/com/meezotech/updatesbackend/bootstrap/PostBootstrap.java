package com.meezotech.updatesbackend.bootstrap;

import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.Media;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import com.meezotech.updatesbackend.repositories.PostRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private GroupRepository groupRepository;
    private PostRepository postRepository;

    public PostBootstrap(GroupRepository groupRepository, PostRepository postRepository) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        postRepository.save(getPosts());
    }

    private List<Post> getPosts() {

        List<Post> posts = new ArrayList<>(2);

        Group group1 = new Group();
        group1.setName("PK Updates");

        groupRepository.save(group1);

        Post post1 = new Post();
        post1.setText("Too much traffic today in khi");

        Media media1 = new Media();
        media1.setType(true);
        media1.setUrl("www.hy.com/s.mp4");
        media1.setPost(post1);

        post1.getMedia().add(media1);

        Post post2 = new Post();
        post2.setText("see this non sense");

        Media media2 = new Media();
        media2.setType(false);
        media2.setUrl("www.hy.com/x.jpg");
        media2.setPost(post2);

        post2.getMedia().add(media2);

        Group group2 = new Group();
        group2.setName("Weather Updates");

        groupRepository.save(group2);

        post1.getGroups().add(group1);
        post2.getGroups().add(group2);

        posts.add(post1);
        posts.add(post2);

        return posts;
    }
}
