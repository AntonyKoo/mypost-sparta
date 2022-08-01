package com.sparta.mypostsparta.controller.dto;

import com.sparta.mypostsparta.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {  // 개별 게시글 조회할 때 보여줄 내용을 요 클래스에 담긔
    // pk 값은 굳이 외부에 노출할 필요 ㄴㄴ
    private String title;  // 게시글 제못

    private String content;  // 게시글 본문

    private LocalDateTime createdAt;  // 생성일

    private LocalDateTime modifiedAt;  // 수정일

    private String userName;  // 작성자

    public PostResponseDto(Post entity) {  // 클라에 보내줄 필요한 정보 꾸러미
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.userName = entity.getUser().getUserName();
    }

}
