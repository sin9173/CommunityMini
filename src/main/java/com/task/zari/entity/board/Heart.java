package com.task.zari.entity.board;

import com.task.zari.entity.BaseTimeEntity;
import com.task.zari.entity.account.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "board_id", "quit"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart extends BaseTimeEntity { //좋아요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; //계정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; //게시판

    public Heart(Account account, Board board) {
        this.account = account;
        this.board = board;
    }
}
