package com.example.demo.service;


import com.example.demo.domain.Group;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.exception.GroupNotFoundException;
import com.example.demo.exception.TrainerIsNotEnoughException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

@Service
@Transactional
public class GroupService {

    private GroupRepository groupRepository;
    private TrainerRepository trainerRepository;
    private TraineeRepository traineeRepository;

    private static final Integer min_trainer_number = 2;

    public GroupService(GroupRepository groupRepository, TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    public List<GroupDto> findGroups() {
        List<GroupDto> groupDtoList = groupRepository.findAll();
        groupDtoList.stream().forEach(groupDto -> {
            groupDto.setTrainees(traineeRepository.findTraineeDtoByGroupId(groupDto.getId()));
            groupDto.setTrainees(trainerRepository.findTrainerDtoByGroupId(groupDto.getId()));
        });
        return groupDtoList;
    }

    public List<GroupDto> autoGrouping(){
        List<TrainerDto> trainerDtoList = trainerRepository.findAll();
        List<TraineeDto> traineeDtoList = traineeRepository.findAll();
        if(trainerDtoList.size()<min_trainer_number) {
            throw new TrainerIsNotEnoughException("当前讲师数量不足");
        }
        int groupNum =trainerDtoList.size()/2;
        groupRepository.saveAll(asList(new GroupDto[groupNum]));
        List<GroupDto> groupDtoList = groupRepository.findAll();

        groupDtoList.forEach(groupDto -> {
            groupDto.setName(groupDto.getId()+" 组");
        });
        IntStream.range(0,traineeDtoList.size())
                .forEach(index ->{
                    traineeDtoList.get(index).setGrouped(true);
                    traineeDtoList.get(index).setGroupId(groupDtoList.get(index%groupNum).getId());
                });
        IntStream.range(0,groupNum)
                .forEach(index -> {
                    trainerDtoList.get(index).setGrouped(true);
                    trainerDtoList.get(index).setGroupId(groupDtoList.get(index).getId());
                    trainerDtoList.get(index+1).setGrouped(true);
                    trainerDtoList.get(index+1).setGroupId(groupDtoList.get(index).getId());
                });

        return this.findGroups();
    }

    public void updateGroupName(Long groupId, String groupName) {
        Optional<GroupDto> optionalGroupDto = groupRepository.findById(groupId);
        if(!optionalGroupDto.isPresent()){
            throw new GroupNotFoundException("此小组不存在");
        }
        GroupDto groupDto = optionalGroupDto.get();
        groupDto.setName(groupName);
    }
}
