package com.task.zari.repository.board;

import com.task.zari.dto.heart.HeartCountDto;
import com.task.zari.dto.response.board.BoardResponseDto;
import com.task.zari.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b.id" +
            " from Board b" +
            " join Heart h on b.id=h.board.id" +
            " where h.account.accountId=:account_id and b.id in :boards")
    Set<Long> findMyHeart(String account_id, @Param("boards") List<Long> boars); //게시글 중 좋아요한 게시글이 있는지 조회

    @Query(value = "select" +
            " new com.task.zari.dto.response.board.BoardResponseDto(b.id, b.title, b.content, a.nickname," +
            " a.accountType, b.id, true, b.quit, b.regDate, b.updateDate, b.deleteDate)" +
            " from Board b" +
            " left outer join Account a on b.account.id=a.id" +
            " left outer join Heart h on b.id=h.board.id" +
            " where h.account.accountId=:account_id" +
            " order by b.id desc")
    Page<BoardResponseDto> findMyHeartList(String account_id, Pageable pageable); //특정 아이디가 좋아요한 게시글 조회

    @Query(value = "select new com.task.zari.dto.heart.HeartCountDto(b.id, count(h.id))" +
            " from Board b" +
            " join Heart h on b.id=h.board.id" +
            " where b.id in :boards" +
            " group by b.id" +
            " order by b.id desc")
    List<HeartCountDto> findHeartCount(@Param("boards") List<Long> boards); //좋아요 개수 조회
}
