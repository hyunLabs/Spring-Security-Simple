package com.hyunlabs.simple.api;

import com.hyunlabs.simple.common.ResultMap;
import com.hyunlabs.simple.config.security.JwtTokenProvider;
import com.hyunlabs.simple.exception.NotFoundException;
import com.hyunlabs.simple.web.Member.Member;
import com.hyunlabs.simple.web.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login/register")
    public UUID register(@Valid @RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        Member loginVO = memberRepository.findById(member.getId());
        if(!passwordEncoder.matches(member.getPassword(), loginVO.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 맞지 앟습니다.");
        }

        return jwtTokenProvider.createToken(loginVO.getId(), loginVO.getRole());
    }
}
