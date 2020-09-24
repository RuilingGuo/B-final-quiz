package com.example.demo.controller;

import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainees")
public class TraineeController {
    @GetMapping()
    public List<TraineeDto> getTraineeList(@RequestParam(value = "grouped",defaultValue = "false") Boolean grouped ){
        return null;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainee(@RequestBody Trainee trainee){

    }
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable(value = "trainee_id") Long id){

    }

}
