package com.example.diary_demo.controller;

import com.example.diary_demo.dto.LoginRequest;
import com.example.diary_demo.dto.SignUpRequest;
import com.example.diary_demo.entity.Member;
import com.example.diary_demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController //외부와 통신하는 api창구
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/signup")
    public String signUp(@RequestBody SignUpRequest request) {
        /*
        프론트는 JSON으로 서버에 텍스트 덩어리 형태로 데이터를 보냄
        백엔드는 객체(Object) 형태로 데이터를 다룸.
        @RequestBody를 사용하면 JSON형태로 받은 데이터를 SignUpRequest의 필드에 맞게 알아서 보관.
        */


        //사용자가 입력한 dto내용을 보고 Member 엔티티에 넣음.
        Member member = new Member();
        member.setLoginId(request.getLoginId());
        member.setPassword(request.getPassword());
        member.setName(request.getName());

        //service에 정보를 넘겨서 회원가입 완료
        Long savedId = memberService.signUp(member);

        //사용자에게 결과 전달.
        return savedId + "번 회원님, 가입이 완료되었습니다.";
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequest request) {

        Long memberId = memberService.login(request.getLoginId(), request.getPassword());

        return memberId + "번 회원님, 환영합니다.";
    }

    // 프론트엔드가 이름을 물어보는 창구
    @GetMapping("/api/members/{memberId}/name")
    public String getMemberName(@PathVariable Long memberId) {
        return memberService.getMemberName(memberId);
    }
}
