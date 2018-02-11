package com.meezotech.updatesbackend.api.v1.model;

public class MediaDTO {

    private String mediaType;
    private String url;
    private String name;
    private String thumbnailUrl;

    public MediaDTO() {
    }

    public MediaDTO(String mediaType, String url, String name, String thumbnailUrl) {
        this.mediaType = mediaType;
        this.url = url;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
