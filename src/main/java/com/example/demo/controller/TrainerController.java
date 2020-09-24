package com.example.demo.controller;


import com.example.demo.domain.Trainer;
import com.example.demo.dto.TrainerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @GetMapping()
    public List<TrainerDto> getTrainerList(@RequestParam(value = "grouped",defaultValue = "false") Boolean grouped ){
        return null;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@RequestBody Trainer trainer){

    }
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable(value = "trainer_id") Long id){

    }

}
