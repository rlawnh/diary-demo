package com.example.diary_demo.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String loginId;
    private String password;
    private String name;
}
