package com.meezotech.updatesbackend.api.v1.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {

    private Long id;
    private Date date;
    private String text;
    private UserDTO userDTO;
    private Set<MediaDTO> media = new HashSet<>();
    private GroupDTO groupDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Set<MediaDTO> getMedia() {
        return media;
    }

    public void setMedia(Set<MediaDTO> media) {
        this.media = media;
    }

    public GroupDTO getGroupDTO() {
        return groupDTO;
    }

    public void setGroupDTO(GroupDTO groupDTO) {
        this.groupDTO = groupDTO;
    }
}
