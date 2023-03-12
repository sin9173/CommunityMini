package com.task.zari.repository.board;

import com.task.zari.entity.account.Account;
import com.task.zari.entity.account.AccountType;
import com.task.zari.entity.board.Board;
import com.task.zari.entity.board.BoardCommunity;
import com.task.zari.entity.board.Heart;
import com.task.zari.repository.account.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommunityRepository communityRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    @Transactional
    public void boardSaveTest() { //게시글, 좋아요 등록 테스트
        Account account = new Account("aaa123", passwordEncoder.encode("password"),
                "nickname", AccountType.Lessee);

        accountRepository.save(account);

        BoardCommunity board = new BoardCommunity("제목1", "내용1", account);

        communityRepository.save(board);

        Heart heart1 = new Heart(account, board);
        Heart heart2 = new Heart(account, board);

        board.addHeart(heart1);
        board.addHeart(heart2);

        Assertions.assertThat(board.getHearts().size()).isEqualTo(2);
    }
}