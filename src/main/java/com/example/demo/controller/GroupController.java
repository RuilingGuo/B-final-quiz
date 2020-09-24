package com.example.demo.controller;

import com.example.demo.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroupList(){
        return null;
    }
    @PostMapping(value = "/auto-grouping")
    public List<GroupDto> autoGrouping(){
        return null;
    }
    @PatchMapping()
    public void updateGroupName(@PathVariable(value = "group_id") Long groupId,
                                @RequestParam(value = "name") String groupName){

    }



}
