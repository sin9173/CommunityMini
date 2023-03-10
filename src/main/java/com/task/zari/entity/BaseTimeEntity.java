package com.task.zari.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(updatable = false)
    private LocalDateTime regDate; // 생성 일시

    private LocalDateTime updateDate; // 수정 일시

    private LocalDateTime deleteDate; //삭제 일시

    @Enumerated(EnumType.STRING)
    private Quit quit; //삭제여부

    @PrePersist
    public void create() {
        LocalDateTime now = LocalDateTime.now();
        regDate = now;
        updateDate = now;
        quit = Quit.ACTIVE;
    }

    @PreUpdate
    public void update() {
        updateDate = LocalDateTime.now();
    }


    public void delete() {
        deleteDate = LocalDateTime.now();
        quit = Quit.QUIT;
    }
}
