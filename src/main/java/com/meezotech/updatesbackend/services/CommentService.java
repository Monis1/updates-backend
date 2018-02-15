package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDTO> getAllCommentsByPostIdPaginated(Pageable pageable, Long postId);

    CommentDTO createComment(CommentDTO commentDTO);

}
