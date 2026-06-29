package com.example.diary_demo.service;

import com.example.diary_demo.dto.DiaryResponse;
import com.example.diary_demo.entity.Diary;
import com.example.diary_demo.entity.Member;
import com.example.diary_demo.repository.DiaryRepository;
import com.example.diary_demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    public DiaryService(DiaryRepository diaryRepository, MemberRepository memberRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
    }

    public Long writeDiary(Long memberId, String title, String content, LocalDate date) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setDate(date);

        diary.setMember(member);

        Diary savedDiary = diaryRepository.save(diary);

        return savedDiary.getId();
    }

    public DiaryResponse getDiaryByDate(Long memberId, LocalDate date) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다"));

        return diaryRepository.findByMemberAndDate(member, date)
                .map(diary -> new DiaryResponse(diary.getId(), diary.getTitle(), diary.getContent()))
                .orElse(null);
    }

    @Transactional
    public void updateDiary(Long diaryId, String title, String content) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 일기입니다."));

        diary.setTitle(title);
        diary.setContent(content);
    }

    public List<LocalDate> getDiaryDates(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return diaryRepository.findAllByMember(member).stream()
                .map(Diary::getDate)
                .toList();
    }
}
