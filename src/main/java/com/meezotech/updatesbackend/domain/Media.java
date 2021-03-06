package com.meezotech.updatesbackend.domain;

import javax.persistence.*;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;

    @Column(nullable = false)
    @Lob
    private String url;

    @Lob
    private String thumbnailUrl;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Post post;

    public Media() {
    }

    public Media(MediaType mediaType, String url, String thumbnailUrl, String name) {
        this.mediaType = mediaType;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.name = name;
    }

    private void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

