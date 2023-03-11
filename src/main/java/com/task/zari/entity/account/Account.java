package com.task.zari.entity.account;

import com.task.zari.dto.request.account.AccountSaveRequestDto;
import com.task.zari.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity { //계정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //인덱스

    @Column(unique = true)
    private String accountId; //계정 ID

    private String password; //비밀번호

    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    private AccountType accountType; //계정타입 (연관관계 주인을 계정으로 설정)


    public Account(String accountId, String password, String nickname, AccountType accountType) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.accountType = accountType;
    }

    public Account(AccountSaveRequestDto dto) {
        this.accountId = dto.getAccount_id();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.accountType = dto.getAccount_type();
    }


}
