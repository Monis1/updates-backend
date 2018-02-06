package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.PostMapper;
import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Page<PostDTO> getAllPostsPaginated(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::postToPostDto);
    }

    @Override
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable, Long groupId) {
        return postRepository.findByGroupId(pageable, groupId).map(postMapper::postToPostDto);
    }

    @Override
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable, Long userId) {
        return postRepository.findByUserId(pageable, userId).map(postMapper::postToPostDto);
    }


}
