package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import com.meezotech.updatesbackend.domain.Comment;

public interface CommentMapper {

    Comment commentDtoToComment(CommentDTO commentDTO);

    CommentDTO commentToCommentDto(Comment comment);

}
