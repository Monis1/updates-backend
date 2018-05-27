package com.meezotech.updatesbackend.bootstrap;

import com.meezotech.updatesbackend.domain.*;
import com.meezotech.updatesbackend.repositories.*;
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
    private CommentRepository commentRepository;
    private DocumentRepository documentRepository;
    private static final Long size = 100L;

    public PostBootstrap(GroupRepository groupRepository,
                         PostRepository postRepository,
                         UserRepository userRepository,
                         CommentRepository commentRepository,
                         DocumentRepository documentRepository) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        postRepository.save(getBulkPosts());

        User user = new User();
        user.setId(1L);

        for (Long i = 1L; i < size; i++) {
            Post post = new Post();
            post.setId(100L);

            Comment comment = new Comment();
            comment.setUser(user);
            comment.setPost(post);
            comment.setDate(new Date());
            comment.setCommentText("Comment " + i);

            post.getComments().add(comment);

            commentRepository.save(comment);
        }

        Document document1 = new Document("about", "MangoMan App is all about fun");
        Document document2 = new Document("rules", "Rules are strict");
        Document document3 = new Document("policy", "Policy is Strict");

        documentRepository.save(document1);
        documentRepository.save(document2);
        documentRepository.save(document3);
    }

    private List<Post> getBulkPosts() {

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
        user.setProfilePictureUrl("https://scontent.fkhi2-1.fna.fbcdn.net/v/t1.0-9/22141210_1527317537304126_6355389030864021810_n.jpg?_nc_cat=0&oh=673a5a3f7f95940b3293b20c8f1f454c&oe=5B7C4971");
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
            post.setApproved(true);
            post.setDate(new Date());

            Media media1 = new Media();
            media1.setMediaType(MediaType.VIDEO);
            media1.setThumbnailUrl("https://imgc.allpostersimages.com/img/print/posters/toy-story-woody-buzz_a-G-13390942-0.jpg");
            media1.setName("Toy Story");
            media1.setUrl("http://www.html5videoplayer.net/videos/toystory.mp4");
            media1.setPost(post);

            Media media2 = new Media();
            media2.setMediaType(MediaType.IMAGE);
            media2.setName("razar");
            media2.setUrl("https://scontent.fkhi2-1.fna.fbcdn.net/v/t1.0-9/11666178_10155855022160112_8830347095374550013_n.jpg?_nc_cat=0&_nc_eui2=AeFevN5pjgXI4c5RwGvu7KZVEoHT1ne3JQO--0-gbvW35yYS1Dy0zXWh1jadn5eqSX5QQLIy4xlvFZPfzzssDvwY2jJAKBzganFyDm029zSlpw&oh=05179dc3f5fe33b34ee8f09c96b6b349&oe=5B98475C");
            media2.setPost(post);

            post.getMedia().add(media1);
            post.getMedia().add(media2);

            if (i % 2 == 0) {
                post.setGroup(group);
                post.setUser(user);
            } else {
                post.setGroup(group1);
                post.setUser(user1);
            }

            posts.add(post);
        }
        Reaction reaction = new Reaction();
        reaction.setPost(posts.get(posts.size() - 1));
        reaction.setUser(user);

        Reaction reaction1 = new Reaction();
        reaction1.setPost(posts.get(posts.size() - 1));
        reaction1.setUser(user1);

        posts.get(posts.size() - 1).getReactions().add(reaction);
        posts.get(posts.size() - 1).getReactions().add(reaction1);

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
