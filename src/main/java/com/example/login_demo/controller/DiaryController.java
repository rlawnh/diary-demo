package com.example.login_demo.controller;

import com.example.login_demo.dto.DiaryResponse;
import com.example.login_demo.dto.UpdateDiaryRequest;
import com.example.login_demo.dto.WriteDiaryRequest;
import com.example.login_demo.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/api/diary")
    public String writeDiary(@RequestBody WriteDiaryRequest request) {

        Long diaryId = diaryService.writeDiary(
                request.getMemberId(),
                request.getTitle(),
                request.getContent(),
                request.getDate()
        );

        return "성공적으로 일기가 저장되었습니다.(생성된 일기 번호: " + diaryId + ")";
    }

    @GetMapping("/api/diary")
    public DiaryResponse getDiary(@RequestParam Long memberId, @RequestParam LocalDate date) {
        return diaryService.getDiaryByDate(memberId, date);
    }

    @GetMapping("/api/diary/dates")
    public List<LocalDate> getDiaryDates(@RequestParam Long memberId) {
        return diaryService.getDiaryDates(memberId);
    }

    @PutMapping("/api/diary/{diaryId}")
    public String updateDiary(@PathVariable Long diaryId, @RequestBody UpdateDiaryRequest request) {
        diaryService.updateDiary(diaryId, request.getTitle(), request.getContent());
        return "일기가 성공적으로 수정되었습니다.";
    }
}
