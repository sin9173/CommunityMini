package com.task.zari.dto.response.board;

import com.task.zari.entity.Quit;
import com.task.zari.entity.account.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class BoardResponseDto { //게시글 목록 응답 데이터

    private Long id; //인덱스

    private String title; //제목

    private String content; //내용

    private String nickname; //닉네임

    private String account_type; //계정타입

    private Long heart_count; // 좋아요 개수

    private boolean my_heart; // 좋아요 여부

    private Quit quit;

    private String reg_date; // 작성일시

    private String update_date; // 수정일시

    private String delete_date; // 삭제일시

    public BoardResponseDto(Long id, String title, String content, String nickname,
                                AccountType accountType, Long heart_count, boolean my_heart, Quit quit,
                                LocalDateTime reg_date, LocalDateTime update_date, LocalDateTime delete_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.account_type = accountType.getName();
        this.heart_count = heart_count;
        this.my_heart = my_heart;

        this.quit = quit;

        this.reg_date = dateFormat(reg_date);
        this.update_date = dateFormat(update_date);
        this.delete_date = dateFormat(delete_date);
    }

    private String dateFormat(LocalDateTime date) {
        if(date==null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
