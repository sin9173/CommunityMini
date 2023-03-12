package com.task.zari.service.board;

import com.task.zari.dto.request.board.community.CommunityListRequestDto;
import com.task.zari.dto.request.board.community.CommunityModifyRequestDto;
import com.task.zari.dto.request.board.community.CommunitySaveRequestDto;
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
import com.task.zari.repository.board.CommunityRepository;
import com.task.zari.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final BoardRepository boardRepository;

    private final CommunityRepository communityRepository;

    private final AccountRepository accountRepository;

    //커뮤니티 글작성
    public SingleResponseDto<BoardIndexResponseDto> communitySave(CommunitySaveRequestDto dto) throws Exception{
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        BoardCommunity board = new BoardCommunity(dto, account);
        communityRepository.save(board);
        return new SingleResponseDto(ResponseResult.SUCCESS, new BoardIndexResponseDto(board.getId()));
    }

    
    //커뮤니티 글 수정
    @Transactional
    public SingleResponseDto<BoardIndexResponseDto> communityModify(Long id, CommunityModifyRequestDto dto) throws Exception{
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        BoardCommunity board = communityRepository.findById(id)
                .orElseThrow(() -> new NoDataException("No Data : " + id));
        if(account.getId()!=board.getAccount().getId()) throw new BadCredentialsException("Not Writer");
        board.updateBoard(dto);
        return new SingleResponseDto(ResponseResult.SUCCESS, new BoardIndexResponseDto(board.getId()));
    }

    //커뮤니티 글 목록 조회
    public SingleResponseDto<Page<CommunityResponseDto>> communityList(CommunityListRequestDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPage(), 10);
        Page<CommunityResponseDto> result = communityRepository.findListAll(pageRequest);
        heartCheck(result);
        return new SingleResponseDto(ResponseResult.SUCCESS, result);
    }

    //자기가 좋아요한 글이 있는지 확인
    private void heartCheck(Page<CommunityResponseDto> result) {
        String account_id = HttpUtils.getUserId();
        //인증헤더가 있을 경우 자기가 좋아요한 글이 있는지 확인
        if(account_id!=null) {
            Set<Long> heart_id_set = boardRepository.findMyHeart(account_id,
                    result.getContent().stream().map(b -> b.getId()).collect(Collectors.toList()));
            result.getContent().stream()
                    .filter(o -> heart_id_set.contains(o.getId()))
                    .forEach(o -> o.setMy_heart(true));
        }
    }
}
