package com.example.demo.repository;

import com.example.demo.dto.TraineeDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<TraineeDto,Long> {

    @Override
    List<TraineeDto> findAll();

    List<TraineeDto> findTraineeDtoByGrouped(Boolean grouped);
}
