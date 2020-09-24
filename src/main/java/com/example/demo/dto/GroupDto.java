package com.example.demo.dto;

import com.example.demo.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private Long id;
    private String name;
    private List<Trainer> trainers;
    private List<Trainer> trainees;
}
