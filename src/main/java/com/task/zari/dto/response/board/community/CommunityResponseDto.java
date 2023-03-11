package com.task.zari.dto.response.board.community;

import com.task.zari.entity.account.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityResponseDto {

    private Long id; //인덱스

    private String title; //제목

    private String content; //내용

    private String nickname; //닉네임

    private String account_type; //계정타입
    
    private Long heart_count; // 좋아요 개수
    
    private boolean my_heart; // 좋아요 여부

    public CommunityResponseDto(Long id, String title, String content, String nickname, AccountType accountType, Long heart_count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.account_type = accountType.getName();
        this.heart_count = heart_count;
    }
}
