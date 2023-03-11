package com.task.zari.controller.board.community;

import com.task.zari.dto.request.board.community.CommunityModifyRequestDto;
import com.task.zari.dto.request.board.community.CommunitySaveRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.board.BoardIndexResponseDto;
import com.task.zari.dto.response.board.community.CommunityResponseDto;
import com.task.zari.service.board.BoardService;
import com.task.zari.service.board.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasAnyRole('LESSOR', 'REALTOR', 'LESSEE')")
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    private final BoardService boardService;

    @PostMapping("/v1/board/community")
    public ResponseEntity<SingleResponseDto<BoardIndexResponseDto>> communitySave(@Valid @RequestBody CommunitySaveRequestDto dto) throws Exception {
        return ResponseEntity.ok().body(communityService.communitySave(dto));
    }

    @PatchMapping("/v1/board/community/{id}")
    public ResponseEntity<SingleResponseDto<BoardIndexResponseDto>> communityModify(@PathVariable Long id, @RequestBody CommunityModifyRequestDto dto) throws Exception {
        return ResponseEntity.ok().body(communityService.communityModify(id, dto));
    }

    @GetMapping("/v1/board/community")
    public ResponseEntity<SingleResponseDto<Page<CommunityResponseDto>>> communityList() {
        return ResponseEntity.ok().body(communityService.communityList());
    }

    @DeleteMapping("/v1/board/community/{id}")
    public ResponseEntity<ResponseDto> communityDelete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(boardService.boardDelete(id));
    }
}
