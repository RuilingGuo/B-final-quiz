package com.example.demo.repository;


import com.example.demo.dto.GroupDto;
import com.example.demo.dto.TrainerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupDto,Long> {

    @Override
    List<GroupDto> findAll();



}
