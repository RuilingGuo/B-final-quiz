package com.example.demo.controller;


import com.example.demo.domain.Trainer;
import com.example.demo.dto.TrainerDto;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainers")
@CrossOrigin
public class TrainerController {
    
    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping()
    public List<TrainerDto> getTrainerList(@RequestParam(value = "grouped",defaultValue = "false") Boolean grouped ){
        return trainerService.findTrainerByGrouped(grouped);

    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@RequestBody @Valid Trainer trainer){
        trainerService.createTrainer(trainer);

    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable Long id){
        trainerService.deleteTrainerByTrainerId(id);

    }

}
