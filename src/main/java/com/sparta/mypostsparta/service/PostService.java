package com.sparta.mypostsparta.service;

import com.sparta.mypostsparta.controller.dto.PostListResponseDto;
import com.sparta.mypostsparta.controller.dto.PostResponseDto;
import com.sparta.mypostsparta.controller.dto.PostSaveRequestDto;
import com.sparta.mypostsparta.controller.dto.PostUpdateRequestDto;
import com.sparta.mypostsparta.domain.Post;
import com.sparta.mypostsparta.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    // 개별 게시글 조회
    @Transactional
    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
        return new PostResponseDto(entity);
    }

    // 게시글 전체 조회
    @Transactional
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }

    // 게시글 수정
    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id)
        );
        if (requestDto.getPassword().equals(post.getPassword())) {  // 이런 로그인 때문에,, 비번 안검..
            post.update(requestDto.getTitle(), requestDto.getContent());
        }
        return id;
    }




}
