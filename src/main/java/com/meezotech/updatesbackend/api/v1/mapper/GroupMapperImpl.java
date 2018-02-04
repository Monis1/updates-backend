package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.domain.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Group groupDtoToGroup(GroupDTO groupDTO) {
        if (groupDTO == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());

        return group;
    }

    @Override
    public GroupDTO groupToGroupDto(Group group) {
        if (group == null) {
            return null;
        }
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());

        return groupDTO;
    }

}
