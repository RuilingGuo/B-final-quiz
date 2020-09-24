package com.example.demo.dto;

import com.example.demo.domain.Trainee;
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
@Table(name = "trainee")
public class TraineeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String office;
    private String email;
    private String zoomId;
    private String github;
    @JsonIgnore
    private Long groupId;
    @JsonIgnore
    private Boolean grouped;

    public static TraineeDto bind(Trainee trainee) {
        return TraineeDto.builder()
                .name(trainee.getName())
                .office(trainee.getOffice())
                .email(trainee.getEmail())
                .zoomId(trainee.getZoomId())
                .github(trainee.getGithub())
                .groupId(null)
                .grouped(false)
                .build();
    }
}
