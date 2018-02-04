package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.domain.Group;

public interface GroupMapper {

    Group groupDtoToGroup(GroupDTO groupDTO);

    GroupDTO groupToGroupDto(Group group);

}
