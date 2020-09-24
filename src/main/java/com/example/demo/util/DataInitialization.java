package com.example.demo.util;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.demo.util.FileUtils.loadSuiteResource;

@Order(value = 1)
@Component
public class DataInitialization implements ApplicationRunner {

    private final TraineeService traineeService;
    private final TrainerService trainerService;

    public DataInitialization(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeData();
    }

    private void initializeData() {
        String traineeJson = loadSuiteResource("trainee.json");
        List<Trainee> traineeList = JsonUtils.unmarshal(traineeJson, new TypeReference<List<Trainee>>() {});
        traineeList.forEach(traineeService::createTrainee);
        String trainerJson = loadSuiteResource("trainer.json");
        List<Trainer> trainerList = JsonUtils.unmarshal(trainerJson, new TypeReference<List<Trainer>>() {});
        trainerList.forEach(trainerService::createTrainer);
    }
}
