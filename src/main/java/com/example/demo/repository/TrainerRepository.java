package com.example.demo.repository;


import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends CrudRepository<TrainerDto,Long> {

    @Override
    List<TrainerDto> findAll();

    List<TrainerDto> findTrainerDtoByGrouped(Boolean grouped);

    List<TraineeDto> findTrainerDtoByGroupId(Long id);
}
