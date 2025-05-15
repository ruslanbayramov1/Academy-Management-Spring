package org.springframework.academymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.academymanagement.dto.group.GroupCreateDTO;
import org.springframework.academymanagement.dto.group.GroupGetDTO;
import org.springframework.academymanagement.dto.group.GroupUpdateDTO;
import org.springframework.academymanagement.entity.Group;
import org.springframework.academymanagement.exception.NotFoundException;
import org.springframework.academymanagement.mapper.GroupMapper;
import org.springframework.academymanagement.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<GroupGetDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(groupMapper::groupToGroupGetDTO).toList();
    }

    public void createGroup(GroupCreateDTO groupCreateDTO) {
        Group group = groupMapper.groupCreateDTOToGroup(groupCreateDTO);
        groupRepository.save(group);
    }

    public GroupGetDTO getGroupById(UUID id) {
        Group group = getGroupEntityById(id);
        return groupMapper.groupToGroupGetDTO(group);
    }

    public void updateGroup(UUID id, GroupUpdateDTO groupUpdateDTO) {
        Group group = getGroupEntityById(id);
        groupMapper.groupUpdateDTOToGroup(groupUpdateDTO, group);
        groupRepository.save(group);
    }

    public void deleteGroup(UUID id) {
        Group group = getGroupEntityById(id);
        groupRepository.deleteById(id);
    }

    private Group getGroupEntityById(UUID id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException("Group not found"));
    }
}
