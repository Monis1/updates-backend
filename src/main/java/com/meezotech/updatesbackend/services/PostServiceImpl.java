package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.PostMapper;
import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.domain.Media;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.utilities.ApiUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Page<PostDTO> getAllPostsPaginated(Pageable pageable, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        Page<Post> postPage = postRepository.findAll(page);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable, Long groupId, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        Page<Post> postPage = postRepository.findByGroupId(page, groupId);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        Page<Post> postPage = postRepository.findByUserId(page, userId);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : postPage) {
            postDTOS.add(postMapper.postToPostDto(post, userId));
        }
        return new PageImpl<>(postDTOS);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.postDtoToPost(postDTO);
        post.setDate(new Date());
        for (Media media:
             post.getMedia()) {
            media.setPost(post);
        }
        return postMapper.postToPostDto(postRepository.save(post), -1L);
    }


}
