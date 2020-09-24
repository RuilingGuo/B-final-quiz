package com.example.demo.controller;

import com.example.demo.domain.Group;
import com.example.demo.dto.GroupDto;
import com.example.demo.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupDto> getGroupList(){
        return groupService.findGroups();
    }
    @PostMapping(value = "/auto-grouping")
    public List<GroupDto> autoGrouping(){
        return groupService.autoGrouping();
    }
    @PatchMapping("/{group_id}")
    public void updateGroupName(@PathVariable(value = "group_id") Long groupId,
                                @RequestParam(value = "name") String groupName){
        groupService.updateGroupName(groupId,groupName);

    }



}
