package com.example.diary_demo.service;

import com.example.diary_demo.entity.Member;
import com.example.diary_demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    //데이터를 넣고 빼기 위해 repository를 불러옴
    private final MemberRepository memberRepository;

    //생성자 주입(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원가입 기능
    */
    public Long signUp(Member member) {
        //중복회원 검증
        validateDuplicateMember(member);

        //검증에 통과했으면 repository에 저장
        Member savedMember = memberRepository.save(member);

        //저장된 회원의 식별자 id 반환
        return savedMember.getId();
    }

    private void validateDuplicateMember(Member member) {
        //optional: 찾으려는 회원id가 없을 수도 있음. 그러면 null값이 반환되는데 그러면 nullpointException이 발생함.
        // -> 이 에러를 방지하기 위해 optional 사용. 그래서 null이 아닌 텅 빈 상자 반환.
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());

        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /*
    로그인 기능
     */
    public Long login(String loginId, String password) {
        // 1. 데이터베이스에서 아이디로 회원 찾기
        // 아까 배운 Optional 상자를 열 때 사용하는 'orElseThrow'라는 마법의 명령어입니다!
        // 상자가 비어있으면(회원이 없으면) 괄호 안의 에러(호루라기)를 불어버립니다.
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

        // 2. 비밀번호 일치 여부 확인
        // 데이터베이스에 저장된 비밀번호와 프론트엔드가 보낸 비밀번호가 다르면 에러!
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 3. 모든 검문을 통과했다면 회원 번호(id)를 돌려줌
        return member.getId();
    }

    public String getMemberName(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return member.getName();
    }
}
