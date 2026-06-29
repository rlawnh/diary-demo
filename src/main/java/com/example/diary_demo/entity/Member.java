package com.example.diary_demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class Member {

    //식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //로그인아이디 - 중복 x, null x
    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
}
