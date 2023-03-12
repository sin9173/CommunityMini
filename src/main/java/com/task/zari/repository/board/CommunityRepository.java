package com.task.zari.repository.board;

import com.task.zari.dto.response.board.community.CommunityResponseDto;
import com.task.zari.entity.board.Board;
import com.task.zari.entity.board.BoardCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<BoardCommunity, Long> {

    @Query(value = "select" +
            " new com.task.zari.dto.response.board.community.CommunityResponseDto(b.id, b.title, b.content, a.nickname," +
            " a.accountType, count(h.id), false, b.quit, b.regDate, b.updateDate, b.deleteDate)" +
            " from BoardCommunity b" +
            " left join fetch Account a on b.account.id=a.id" +
            " left outer join Heart h on b.id=h.board.id" +
            " group by b.id order by b.id desc",
            countQuery = "select count(b) from Board b")
    Page<CommunityResponseDto> findListAll(Pageable pageable); //게시글 조회



}
