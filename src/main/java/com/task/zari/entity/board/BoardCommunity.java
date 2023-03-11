package com.task.zari.entity.board;

import com.task.zari.dto.request.board.community.CommunityModifyRequestDto;
import com.task.zari.dto.request.board.community.CommunitySaveRequestDto;
import com.task.zari.entity.account.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DynamicUpdate
@DiscriminatorValue("COMMUNITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommunity extends Board {

    public BoardCommunity(String title, String content, Account account) {
        super(title, content, account);
    }

    public BoardCommunity(CommunitySaveRequestDto dto, Account account) {
        super(dto.getTitle(), dto.getContent(), account);
    }

    public void updateBoard(CommunityModifyRequestDto dto) {
        super.updateBoard(dto.getTitle(), dto.getContent());
    }
}
