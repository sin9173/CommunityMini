package com.task.zari.entity.board;

import com.task.zari.entity.BaseTimeEntity;
import com.task.zari.entity.Quit;
import com.task.zari.entity.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Board extends BaseTimeEntity { //게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //제목

    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; //계정

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Heart> hearts = new ArrayList<>(); //좋아요

    protected Board(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.account = account;
    }

    protected void updateBoard(String title, String content) { //게시판 수정
        if(StringUtils.hasText(title)) this.title = title;
        if(StringUtils.hasText(content)) this.content = content;
    }

    public void addHeart(Heart heart) { //좋아요 추가
        this.hearts.add(heart);
    }
}
