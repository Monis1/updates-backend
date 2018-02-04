package com.meezotech.updatesbackend.api.v1.model;

public class MediaDTO {

    private String mediaType;
    private String url;

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
