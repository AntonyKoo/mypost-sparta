package com.sparta.mypostsparta.controller;


import com.sparta.mypostsparta.controller.dto.PostListResponseDto;
import com.sparta.mypostsparta.controller.dto.PostResponseDto;
import com.sparta.mypostsparta.controller.dto.PostSaveRequestDto;
import com.sparta.mypostsparta.controller.dto.SignupRequestDto;
import com.sparta.mypostsparta.service.PostService;
import com.sparta.mypostsparta.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor // 이걸로 서비스 초기화 함
@RestController
@Controller
public class PostApiController {

    private final PostService postService;

    private final UsersService usersService;

    @PostMapping("/api/post")  // 게시글 작성, 몬가 JSON 형태로 데이터를 주겠지?
    public Long save(@RequestBody PostSaveRequestDto requestDto) {return postService.save(requestDto);}

    @GetMapping("/api/posts")  // 게시글 전체 조회
    public List<PostListResponseDto> viewAll() {return postService.findAllDesc();}

    @GetMapping("/api/post/{id}")  // 게시글 개별 조회
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

}
