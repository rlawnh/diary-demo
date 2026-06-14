package com.example.login_demo.repository;

import com.example.login_demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
//위의 <Member, Long>은 Member의 식별자가 Long타입이라는 뜻.

    //아래의 코드는 식별자 번호(Long)말고 loginId를 넘겨줄테니 회원을 찾아달라는 코드.
    Optional<Member> findByLoginId(String loginId);



}
