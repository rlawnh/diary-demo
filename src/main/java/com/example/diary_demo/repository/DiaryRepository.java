package com.example.diary_demo.repository;

import com.example.diary_demo.entity.Diary;
import com.example.diary_demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    //특정 회원이 쓴 모든 일기를 목록으로 가져오기
    List<Diary> findAllByMember(Member member);

    //특정 회원이 특정날짜에 쓴 일기 찾기
    Optional<Diary> findByMemberAndDate(Member member, LocalDate date);
}
