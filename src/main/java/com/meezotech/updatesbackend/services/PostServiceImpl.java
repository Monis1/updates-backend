package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.PostMapper;
import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.api.v1.model.PostListDTO;
import com.meezotech.updatesbackend.controllers.UserBlockedException;
import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.Media;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.notifications.NotificationUtility;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.repositories.UserRepository;
import com.meezotech.updatesbackend.utilities.ApiUtility;
import com.meezotech.updatesbackend.utilities.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private PostMapper postMapper;
    private NotificationUtility notificationUtility;

    public PostServiceImpl(PostRepository postRepository,
                           GroupRepository groupRepository,
                           UserRepository userRepository,
                           PostMapper postMapper,
                           NotificationUtility notificationUtility) {
        this.postRepository = postRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
        this.notificationUtility = notificationUtility;
    }

    @Override
    public Page<PostDTO> getAllPostsPaginated(Pageable pageable, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, Constants.SORT_PROPERTY_ID);
        Page<Post> postPage = postRepository.findByGroup_DeletedAndApproved(page, false, true);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable, Long groupId, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, Constants.SORT_PROPERTY_ID);
        Page<Post> postPage = postRepository.findByGroupIdAndGroup_DeletedAndApproved(page, groupId, false, true);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, Constants.SORT_PROPERTY_ID);
        Page<Post> postPage = postRepository.findByUserIdAndGroup_DeletedAndApproved(page, userId, false, true);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.postDtoToPost(postDTO);
        if (groupRepository.findByIdAndBannedUsers(post.getGroup().getId(), post.getUser()) != null)
            throw new UserBlockedException(Constants.USER_BLOCKED_MESSAGE);
        post.setDate(new Date());
        for (Media media :
                post.getMedia()) {
            media.setPost(post);
        }
        if (groupRepository.findOne(post.getGroup().getId()).isTypeApproval())
            post.setApproved(false);
        else
            post.setApproved(true);
        post = postRepository.save(post);
        List<User> users = userRepository.findAllByIdIsNot(post.getUser().getId());
        String[] tokens = new String[users.size()];
        for (int i = 0; i < users.size(); i++)
            tokens[i] = users.get(i).getNotificationToken();
        notificationUtility.sendPostNotification(post, tokens);
        return postMapper.postToPostDto(post, -1L);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(id);
    }

    @Override
    public void approvePost(Long postId, boolean isApproved) {
        Post post = postRepository.findOne(postId);
        post.setApproved(isApproved);
        postRepository.save(post);
    }

    @Override
    public PostListDTO getAllPostsForGroupAdmin(long groupId) {
        List<Post> posts = postRepository.findByGroup_Id(groupId);
        return getPostListDTO(posts);
    }

    private PostListDTO getPostListDTO(List<Post> posts) {
        PostListDTO postListDTO = new PostListDTO();
        for (Post post :
                posts) {
            postListDTO.getPosts().add(postMapper.postToPostDto(post, -1L));
        }
        return postListDTO;
    }

    @Override
    public PostListDTO getAnnouncements(Long groupId) {
        List<Post> posts = postRepository.findByGroup_IdAndFromAdmin(groupId, true);
        return getPostListDTO(posts);
    }

    @Override
    public boolean createAdminPost(String text, Long groupId) {
        Group group = groupRepository.findOne(groupId);
        Post post = new Post();
        post.setText(text);
        post.setFromAdmin(true);
        post.setDate(new Date());
        post.setGroup(group);
        postMapper.postToPostDto(postRepository.save(post), -1L);
        return true;
    }

    @Override
    public PostDTO savePostByDTO(Long postId, PostDTO postDTO) {
        Post post = postMapper.postDtoToPost(postDTO);
        if (groupRepository.findByIdAndBannedUsers(post.getGroup().getId(), post.getUser()) != null)
            throw new UserBlockedException(Constants.USER_BLOCKED_MESSAGE);
        post.setId(postId);
        if (groupRepository.findOne(post.getGroup().getId()).isTypeApproval())
            post.setApproved(false);
        else
            post.setApproved(true);
        return postMapper.postToPostDto(postRepository.save(post), -1L);
    }

    @Override
    public PostDTO getPostById(long id, Long userId) {
        return postMapper.postToPostDto(postRepository.findOne(id), userId);
    }

}
