package com.meezotech.updatesbackend.domain;

import javax.persistence.*;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean type;

    private String url;

    @ManyToOne
    private Post post;

    private void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return id;
    }

    public void setType(boolean value) {
        this.type = value;
    }

    public boolean getType() {
        return type;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return url;
    }

    public void setPost(Post value) {
        this.post = value;
    }

    public Post getPost() {
        return post;
    }

}

