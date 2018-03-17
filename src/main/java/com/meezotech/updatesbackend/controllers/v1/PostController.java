package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PostController.BASE_URL)
public class PostController {

    static final String BASE_URL = "/api/v1/post";

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsPaginated(Pageable pageable, @RequestParam("userId") Long userId){
        return postService.getAllPostsPaginated(pageable, userId);
    }

    @GetMapping("/group")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable,
                                                       @RequestParam("groupId") Long groupId,
                                                       @RequestParam("userId") Long userId){
        return postService.getAllPostsByGroupIdPaginated(pageable, groupId, userId);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable,
                                                      @RequestParam("userId") Long userId){
        return postService.getAllPostsByUserIdPaginated(pageable, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

}
