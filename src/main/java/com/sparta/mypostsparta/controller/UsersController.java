package com.sparta.mypostsparta.controller;

import com.sparta.mypostsparta.controller.dto.SignupRequestDto;
import com.sparta.mypostsparta.controller.dto.UserInfoDto;
import com.sparta.mypostsparta.domain.enums.UserRole;
import com.sparta.mypostsparta.security.UserDetailsImpl;
import com.sparta.mypostsparta.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UsersController {

    private final UsersService usersService;
    
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService; // 믱
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")  
    public String signup() {return "signup";}


    // 회원정보 저장 -> 전체 게시글로 이동
    @PostMapping("/user/signup")  
    public  String saveUserInfo(SignupRequestDto signupRequestDto) {
        usersService.saveUserInfo(signupRequestDto);
        return "redirect:/api/posts";
    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUserName();
        UserRole role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRole.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }


}
