package com.example.demo.dto;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "trainer")
public class TrainerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    private Long groupId;
    @JsonIgnore
    private Boolean grouped;

    public static TrainerDto bind(Trainer trainer) {
        return TrainerDto.builder()
                .name(trainer.getName())
                .groupId(null)
                .grouped(false)
                .build();
    }

}
