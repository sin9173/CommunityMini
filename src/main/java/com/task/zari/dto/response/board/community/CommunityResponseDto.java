package com.task.zari.dto.response.board.community;

import com.task.zari.dto.response.board.BoardResponseDto;
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
public class CommunityResponseDto extends BoardResponseDto { //커뮤니티 게시글 목록 응답 데이터

    public CommunityResponseDto(Long id, String title, String content, String nickname,
                                AccountType accountType, Long heart_count, boolean my_heart, Quit quit,
                                LocalDateTime reg_date, LocalDateTime update_date, LocalDateTime delete_date) {
        super(id, title, content, nickname, accountType, heart_count, my_heart, quit, reg_date, update_date, delete_date);
    }

}
