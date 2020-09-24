package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainee {
    private String name;
    private String office;
    private String email;
    private String zoomId;
    private String github;

}
