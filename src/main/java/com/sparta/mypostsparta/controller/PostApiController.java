package com.sparta.mypostsparta.controller;


import com.sparta.mypostsparta.controller.dto.PostResponseDto;
import com.sparta.mypostsparta.controller.dto.PostSaveRequestDto;
import com.sparta.mypostsparta.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 이걸로 서비스 초기화 함
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/post")  // 몬가 JSON 형태로 데이터를 주겠지?
    public Long save(@RequestBody PostSaveRequestDto requestDto) {return postService.save(requestDto);}

    @GetMapping("/api/post/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
