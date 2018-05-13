package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.api.v1.model.GroupListDTO;

public interface GroupService {

    GroupListDTO getAllGroups();
    GroupDTO createGroup(GroupDTO groupDTO);
    void hideGroup(GroupDTO groupDTO);
    void unHideGroup(GroupDTO groupDTO);
    GroupDTO getGroupById(Long groupId);
    boolean doesAdminExists(long groupId, String password);

}
