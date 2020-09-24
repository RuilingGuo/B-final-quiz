package com.example.demo.controller;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import com.example.demo.service.TraineeService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    private TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping()
    public List<TraineeDto> getTraineeList(@RequestParam(value = "grouped",defaultValue = "false") Boolean grouped ){
        return traineeService.findTraineesByGrouped(grouped);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainee(@RequestBody @Valid Trainee trainee){
        traineeService.createTrainee(trainee);
    }
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable(value = "trainee_id") Long id){
        traineeService.deleteTraineeByTraineeId(id);
    }

}
