package com.sparta.mypostsparta.controller.dto;

import com.sparta.mypostsparta.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private String title;  // 게시글 제목
    private String userName;  // 작성자 이름

    private LocalDateTime createdAt;  // 생성일

    // 이부분은 협의에 따라 제거 또는 생성일보다 최근일 경우 대체하는 메쏘 구성
    private LocalDateTime modifiedAt;  // 수정일

    @Builder  // 빌더 클래스가 생성되어 setter 대신 각 필드값을 채워줌
    public PostListResponseDto(Post entity) {
        this.title = entity.getTitle();
        this.userName = entity.getUser().getUserName();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}
