package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.api.v1.model.PostListDTO;
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

    @GetMapping("/single")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPostById(@RequestParam("postId") long postId,
                               @RequestParam("userId") long userId){
        return postService.getPostById(postId, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsPaginated(Pageable pageable, @RequestParam("userId") Long userId) {
        return postService.getAllPostsPaginated(pageable, userId);
    }

    @GetMapping("/Announcements")
    @ResponseStatus(HttpStatus.OK)
    public PostListDTO getAnnouncements(@RequestParam("groupId") Long groupId) {
        return postService.getAnnouncements(groupId);
    }

    @GetMapping("/group")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsByGroupIdPaginated(Pageable pageable,
                                                       @RequestParam("groupId") Long groupId,
                                                       @RequestParam("userId") Long userId) {
        return postService.getAllPostsByGroupIdPaginated(pageable, groupId, userId);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDTO> getAllPostsByUserIdPaginated(Pageable pageable,
                                                      @RequestParam("userId") Long userId) {
        return postService.getAllPostsByUserIdPaginated(pageable, userId);
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public PostListDTO getAllPostsForGroupAdmin(@RequestParam("groupId") long groupId) {
        return postService.getAllPostsForGroupAdmin(groupId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @PutMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean createAdminPost(@RequestParam("text") String text, @RequestParam("groupId") Long groupId) {
        return postService.createAdminPost(text, groupId);
    }

    @PutMapping("/admin/approval")
    @ResponseStatus(HttpStatus.OK)
    public void approvePost(@RequestParam("postId") Long postId, @RequestParam("isApproved") boolean isApproved) {
        postService.approvePost(postId, isApproved);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@RequestParam("postId") Long postId) {
        postService.deletePost(postId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDTO updatePost(@RequestParam("postId") Long postId, @RequestBody PostDTO postDTO) {
        return postService.savePostByDTO(postId, postDTO);
    }

    @PutMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public void reportPost(@RequestParam("postId") Long postId, @RequestParam("userId") Long userId) {
        postService.reportPost(postId, userId);
    }

}
