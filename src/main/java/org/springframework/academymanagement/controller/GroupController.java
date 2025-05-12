package org.springframework.academymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.academymanagement.dto.group.GroupCreateDTO;
import org.springframework.academymanagement.dto.group.GroupGetDTO;
import org.springframework.academymanagement.dto.group.GroupUpdateDTO;
import org.springframework.academymanagement.dto.response.ApiResponse;
import org.springframework.academymanagement.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GroupGetDTO>>> getGroups() {
        ApiResponse<List<GroupGetDTO>> response = new ApiResponse<>(true, HttpStatus.OK, "success", groupService.getAllGroups());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ApiResponse<GroupGetDTO>> getGroupById(@PathVariable UUID groupId) {
        ApiResponse<GroupGetDTO> response = new ApiResponse<>(true, HttpStatus.OK, "success", groupService.getGroupById(groupId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createGroup(@RequestBody @Valid GroupCreateDTO groupCreateDTO) {
        groupService.createGroup(groupCreateDTO);
        ApiResponse<Void> response = new ApiResponse<>(true, HttpStatus.CREATED, "created");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Void>> updateGroup(@RequestHeader UUID id, @RequestBody @Valid GroupUpdateDTO groupUpdateDTO) {
        groupService.updateGroup(id, groupUpdateDTO);
        ApiResponse<Void> response = new ApiResponse<>(true, HttpStatus.CREATED, "updated");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteGroup(@RequestHeader UUID id) {
        groupService.deleteGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
