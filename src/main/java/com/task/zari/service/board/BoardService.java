package com.task.zari.service.board;

import com.task.zari.dto.heart.HeartCountDto;
import com.task.zari.dto.request.board.BoardHeartListRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.board.BoardResponseDto;
import com.task.zari.entity.account.Account;
import com.task.zari.entity.board.Board;
import com.task.zari.exception.NoAccountException;
import com.task.zari.exception.NoDataException;
import com.task.zari.repository.account.AccountRepository;
import com.task.zari.repository.board.BoardRepository;
import com.task.zari.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final AccountRepository accountRepository;

    //게시글 삭제
    @Transactional
    public ResponseDto boardDelete(Long id) throws Exception {
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoDataException("No Data"));
        if(account.getId()!=board.getAccount().getId()) throw new BadCredentialsException("Not Writer");
        board.delete();
        return new ResponseDto(ResponseResult.SUCCESS);
    }

    
    //특정 사용자가 좋아요한 글 목록 조회
    public SingleResponseDto<Page<BoardResponseDto>> boardHeartList(BoardHeartListRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), 10);
        Page<BoardResponseDto> result = boardRepository.findMyHeartList(dto.getAccount_id(), pageable);
        heartCount(result);
        return new SingleResponseDto(ResponseResult.SUCCESS, result);
    }

    //게시글들의 좋아요 개수 가져오기
    private void heartCount(Page<BoardResponseDto> result) {
        List<HeartCountDto> heartCounts = boardRepository.findHeartCount(result.getContent().stream()
                .map(b -> b.getId()).collect(Collectors.toList()));
        AtomicInteger index = new AtomicInteger();
        result.getContent().stream()
                .forEach(b -> b.setHeart_count(heartCounts.get(index.getAndIncrement()).getHeart_count()));
    }
}
