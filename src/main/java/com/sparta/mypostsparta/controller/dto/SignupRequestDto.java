package com.sparta.mypostsparta.controller.dto;

import com.sparta.mypostsparta.domain.Users;
import com.sparta.mypostsparta.domain.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignupRequestDto {

    private String userName;  // 사용자 아이디

    private String password;  // 비밀번호

    private boolean admin = false; // 관리자 필드, 초기화 됨

    private String adminToken = "";  // 가입창에서 입력한 관리자 비번, 초기화 됨

}
