package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.GroupMapper;
import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.api.v1.model.GroupListDTO;
import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupListDTO getAllGroups() {
        Set<Group> groups = new HashSet<>();
        groupRepository.findAll().iterator().forEachRemaining(groups::add);
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Group group :
                groups) {
            groupDTOS.add(groupMapper.groupToGroupDto(group));
        }
        return new GroupListDTO(groupDTOS);
    }

    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) {
        Group group = groupMapper.groupDtoToGroup(groupDTO);
        return groupMapper.groupToGroupDto(groupRepository.save(group));
    }

}
