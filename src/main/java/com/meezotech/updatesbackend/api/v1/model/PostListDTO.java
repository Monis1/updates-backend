package com.meezotech.updatesbackend.api.v1.model;

import java.util.ArrayList;
import java.util.List;

public class PostListDTO {

    private List<PostDTO> posts;

    public PostListDTO(){
        posts = new ArrayList<>();
    }

    public PostListDTO(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
