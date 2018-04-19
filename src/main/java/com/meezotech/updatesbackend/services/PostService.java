package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostDTO> getAllPostsPaginated(Pageable pageable, Long userId);

    Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable, Long groupId, Long userId);

    Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable, Long UserId);

    PostDTO createPost(PostDTO postDTO);

    void deletePost(Long id);

}
