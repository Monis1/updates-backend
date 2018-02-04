package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostDTO> getAllPostsByPage(Pageable pageable);

}
