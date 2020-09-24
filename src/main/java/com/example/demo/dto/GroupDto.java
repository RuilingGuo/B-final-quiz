package com.example.demo.dto;

import com.example.demo.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "group")
public class GroupDto {
    private Long id;
    private String name;
    private List<Trainer> trainers;
    private List<Trainer> trainees;
}
