package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import com.example.demo.exception.PeopleNotFoundException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TraineeService {

    private TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<TraineeDto> findTraineeByGrouped(Boolean grouped) {
        return traineeRepository.findTraineeDtoByGrouped(grouped);
    }

    public void createTrainee(Trainee trainee) {
        traineeRepository.save(TraineeDto.bind(trainee));
    }

    public void deleteTraineeByTraineeId(Long id) {
        if(!traineeRepository.existsById(id)){
            throw new PeopleNotFoundException("此学员不存在");
        }
        traineeRepository.deleteById(id);
    }
}
