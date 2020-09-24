package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    private String name;
    private List<Trainer> trainers;
    private List<Trainer> trainees;
}
