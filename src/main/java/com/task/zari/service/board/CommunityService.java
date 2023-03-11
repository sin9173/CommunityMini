package com.task.zari.service.board;

import com.task.zari.dto.request.board.community.CommunityModifyRequestDto;
import com.task.zari.dto.request.board.community.CommunitySaveRequestDto;
import com.task.zari.dto.response.ListResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.board.BoardIndexResponseDto;
import com.task.zari.dto.response.board.community.CommunityResponseDto;
import com.task.zari.entity.account.Account;
import com.task.zari.entity.board.BoardCommunity;
import com.task.zari.exception.NoAccountException;
import com.task.zari.exception.NoDataException;
import com.task.zari.repository.account.AccountRepository;
import com.task.zari.repository.board.BoardRepository;
import com.task.zari.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final BoardRepository boardRepository;

    private final AccountRepository accountRepository;

    public SingleResponseDto<BoardIndexResponseDto> communitySave(CommunitySaveRequestDto dto) throws Exception{
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        BoardCommunity board = new BoardCommunity(dto, account);
        boardRepository.save(board);
        return new SingleResponseDto(ResponseResult.SUCCESS, new BoardIndexResponseDto(board.getId()));
    }

    @Transactional
    public SingleResponseDto<BoardIndexResponseDto> communityModify(Long id, CommunityModifyRequestDto dto) throws Exception{
        BoardCommunity board = (BoardCommunity) boardRepository.findById(id)
                .orElseThrow(() -> new NoDataException("No Data : " + id));
        board.updateBoard(dto);
        return new SingleResponseDto(ResponseResult.SUCCESS, new BoardIndexResponseDto(board.getId()));
    }

    public SingleResponseDto<Page<CommunityResponseDto>> communityList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CommunityResponseDto> result = boardRepository.findListAll(pageRequest);
        Set<Long> heart_id_set = boardRepository.findMyHeart(HttpUtils.getUserId());
        result.getContent().stream()
                .filter(o -> heart_id_set.contains(o.getId()))
                .forEach(o -> o.setMy_heart(true));
        return new SingleResponseDto(ResponseResult.SUCCESS, result);
    }
}
