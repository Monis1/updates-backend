package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.GroupMapper;
import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.api.v1.model.GroupListDTO;
import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.GroupAdmin;
import com.meezotech.updatesbackend.repositories.GroupAdminRepository;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private GroupAdminRepository groupAdminRepository;
    private GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupAdminRepository groupAdminRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupAdminRepository = groupAdminRepository;
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
        group = groupRepository.save(group);
        createGroupAdmin(groupDTO, group);
        return groupMapper.groupToGroupDto(group);
    }

    private void createGroupAdmin(GroupDTO groupDTO, Group group) {
        GroupAdmin groupAdmin = new GroupAdmin(group.getId(), groupDTO.getAdminName(),
                groupDTO.getAdminEmail(), groupDTO.getAdminPassword());
        groupAdminRepository.save(groupAdmin);
    }

    @Override
    public void hideGroup(GroupDTO groupDTO) {
        Group group = groupMapper.groupDtoToGroup(groupDTO);
        group.setDeleted(true);
        groupRepository.save(group);
    }

    @Override
    public void unHideGroup(GroupDTO groupDTO) {
        Group group = groupMapper.groupDtoToGroup(groupDTO);
        group.setDeleted(false);
        groupRepository.save(group);
    }

    @Override
    public GroupDTO getGroupById(Long groupId) {
        return  groupMapper.groupToGroupDto(groupRepository.findOne(groupId));
    }

    @Override
    public boolean doesAdminExists(long groupId, String password) {
        return groupAdminRepository.findByGroupIdAndPassword(groupId, password).isPresent();
    }

}
