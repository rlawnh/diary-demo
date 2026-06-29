package com.example.diary_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String title;
    private String content;
}
