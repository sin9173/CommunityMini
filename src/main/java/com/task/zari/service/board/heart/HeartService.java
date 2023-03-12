package com.task.zari.service.board.heart;

import com.task.zari.dto.request.board.heart.HeartSaveRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.entity.account.Account;
import com.task.zari.entity.board.Board;
import com.task.zari.entity.board.Heart;
import com.task.zari.exception.NoAccountException;
import com.task.zari.exception.NoDataException;
import com.task.zari.repository.account.AccountRepository;
import com.task.zari.repository.board.CommunityRepository;
import com.task.zari.repository.board.HeartRepository;
import com.task.zari.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    private final CommunityRepository communityRepository;

    private final AccountRepository accountRepository;

    @Transactional
    public ResponseDto heartSave(HeartSaveRequestDto dto) throws Exception { //좋아요 등록
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        Board board = communityRepository.findById(dto.getBoard_id())
                .orElseThrow(() -> new NoDataException("No Data"));

        heartRepository.save(new Heart(account, board));
        return new ResponseDto(ResponseResult.SUCCESS);
    }
}
