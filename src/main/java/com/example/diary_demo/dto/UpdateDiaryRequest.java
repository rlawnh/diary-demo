package com.example.diary_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateDiaryRequest {

    private String title;
    private String content;
}
