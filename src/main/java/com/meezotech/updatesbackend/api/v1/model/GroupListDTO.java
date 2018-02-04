package com.meezotech.updatesbackend.api.v1.model;

import java.util.List;

public class GroupListDTO {

    private List<GroupDTO> groups;

    public GroupListDTO(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }
}
