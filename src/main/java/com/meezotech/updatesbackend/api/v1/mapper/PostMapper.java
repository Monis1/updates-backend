package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;

public interface PostMapper {

    PostDTO postToPostDto(Post post, Long userId);
    Post postDtoToPost(PostDTO postDTO);


}
