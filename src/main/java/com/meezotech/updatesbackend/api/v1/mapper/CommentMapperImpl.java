package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import com.meezotech.updatesbackend.domain.Comment;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {

    private UserMapper userMapper;

    public CommentMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Comment commentDtoToComment(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();

        Post post = new Post();
        post.setId(commentDTO.getPostId());

        comment.setCommentText(commentDTO.getCommentText());
        comment.setPost(post);
        comment.setUser(userMapper.userDtoToUser(commentDTO.getUser()));
        comment.setDate(commentDTO.getDate());
        comment.setId(commentDTO.getId());

        return comment;
    }

    @Override
    public CommentDTO commentToCommentDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return new CommentDTO(comment.getId(), userMapper.userToUserDto(comment.getUser()),
                comment.getPost().getId(), comment.getCommentText(), comment.getDate());
    }

}
