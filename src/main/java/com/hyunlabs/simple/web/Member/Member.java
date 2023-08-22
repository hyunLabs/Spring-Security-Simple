package com.hyunlabs.simple.web.Member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class Member {

    private UUID uuid;
    // 숫자나 영문자만 사용가능
    // 3 ~ 20자 사용 가능
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[0-9a-zA-Z]*$")
    private String id;
    private String password;
    private String nickname;
    private Role role;
}
