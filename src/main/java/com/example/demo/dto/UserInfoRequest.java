package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInfoRequest {
    @NotBlank
    private String email;
    private String username;
    private String fullname;
}
