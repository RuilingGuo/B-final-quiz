package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraineeDto {
    private Long id;
    private String name;
    private String office;
    private String email;
    private String zoomId;
    private String github;

}
