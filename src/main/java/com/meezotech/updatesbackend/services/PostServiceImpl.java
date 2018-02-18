package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.PostMapper;
import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Media;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.utilities.ApiUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        return postRepository.findAll(page).map(postMapper::postToPostDto);
    }

    @Override
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable, Long groupId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        return postRepository.findByGroupId(page, groupId).map(postMapper::postToPostDto);
    }

    @Override
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable, Long userId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, "id");
        return postRepository.findByUserId(page, userId).map(postMapper::postToPostDto);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.postDtoToPost(postDTO);
        post.setDate(new Date());
        for (Media media:
             post.getMedia()) {
            media.setPost(post);
        }
        return postMapper.postToPostDto(postRepository.save(post));
    }


}
