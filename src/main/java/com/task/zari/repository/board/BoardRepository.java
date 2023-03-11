package com.task.zari.repository.board;

import com.task.zari.dto.response.board.community.CommunityResponseDto;
import com.task.zari.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select" +
            " new com.task.zari.dto.response.board.community.CommunityResponseDto(b.id, b.title, b.content, a.nickname, a.accountType, count(h.id))" +
            " from Board b" +
            " left join fetch Account a on b.account.id=a.id" +
            " left outer join Heart h on b.id=h.board.id" +
            " group by b.id",
            countQuery = "select b from Board b")
    Page<CommunityResponseDto> findListAll(Pageable pageable);

    @Query(value = "select b.id" +
            " from Board b" +
            " join Heart h on b.id=h.board.id" +
            " where h.account.accountId=:account_id")
    Set<Long> findMyHeart(String account_id);

}
