package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainee {
    @NotBlank
    private String name;
    @NotBlank
    private String office;
    @Email
    private String email;
    @NotBlank
    private String zoomId;
    private String github;

}
