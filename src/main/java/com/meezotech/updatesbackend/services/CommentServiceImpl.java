package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.CommentMapper;
import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import com.meezotech.updatesbackend.domain.Comment;
import com.meezotech.updatesbackend.repositories.CommentRepository;
import com.meezotech.updatesbackend.utilities.ApiUtility;
import com.meezotech.updatesbackend.utilities.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public Page<CommentDTO> getAllCommentsByPostIdPaginated(Pageable pageable, Long postId) {
        final PageRequest page = ApiUtility.getPageRequestWithSorting(pageable, Constants.SORT_PROPERTY_ID);
        return commentRepository.findByPostId(page, postId).map(commentMapper::commentToCommentDto);
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        commentDTO.setDate(new Date());
        return commentMapper.commentToCommentDto(commentRepository.save(commentMapper.commentDtoToComment(commentDTO)));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    @Override
    public CommentDTO saveCommentByDTO(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentMapper.commentDtoToComment(commentDTO);
        comment.setId(commentId);
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

}
