package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import com.meezotech.updatesbackend.domain.Comment;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoToComment(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();

        User user = new User();
        user.setId(commentDTO.getUserId());

        Post post = new Post();
        post.setId(commentDTO.getPostId());

        comment.setCommentText(commentDTO.getCommentText());
        comment.setPost(post);
        comment.setUser(user);
        comment.setDate(commentDTO.getDate());

        return comment;
    }

    @Override
    public CommentDTO commentToCommentDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return new CommentDTO(comment.getUser().getId(),
                comment.getPost().getId(), comment.getCommentText(), comment.getDate());
    }

}
