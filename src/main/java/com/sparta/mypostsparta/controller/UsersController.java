package com.sparta.mypostsparta.controller;

import com.sparta.mypostsparta.controller.dto.SignupRequestDto;
import com.sparta.mypostsparta.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {

    private final UsersService usersService;
    
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService; // 믱
    }

//    @GetMapping("/")
//    public String homeIsPosts() {
//        return "redirect:/api/posts";
//    }

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
        return "redirect:/user/loginView";
    }

}
