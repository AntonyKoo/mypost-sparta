package com.sparta.mypostsparta.service;

import com.sparta.mypostsparta.controller.dto.PostListResponseDto;
import com.sparta.mypostsparta.controller.dto.PostResponseDto;
import com.sparta.mypostsparta.controller.dto.PostSaveRequestDto;
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

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
        return new PostResponseDto(entity);
    }

    @Transactional
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }
}
