package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.CommentDTO;
import com.meezotech.updatesbackend.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    static final String BASE_URL = "/api/v1/comment";

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDTO> getAllCommentsByPostIdPaginated(Pageable pageable, @RequestParam("postId") Long postId){
        return commentService.getAllCommentsByPostIdPaginated(pageable, postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }

}