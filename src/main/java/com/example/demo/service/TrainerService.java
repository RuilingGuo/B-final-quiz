package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainerService {

    private TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<TrainerDto> findTrainerByGrouped(Boolean grouped) {
        return trainerRepository.findTrainerDtoByGrouped(grouped);
    }

    public void createTrainer(Trainer trainer) {
        trainerRepository.save(TrainerDto.bind(trainer));
    }

    public void deleteTrainerByTrainerId(Long id) {
        trainerRepository.deleteById(id);
    }
}
