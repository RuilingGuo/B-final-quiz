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
    @NotBlank(message = "名字不能为空")
    private String name;
    @NotBlank(message = "办公室不能为空")
    private String office;
    @Email(message = "邮箱地址不正确")
    private String email;
    @NotBlank(message = "Zoom meeting ID不能为空")
    private String zoomId;
    private String github;

}
