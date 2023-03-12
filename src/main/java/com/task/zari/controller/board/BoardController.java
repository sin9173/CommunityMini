package com.task.zari.controller.board;

import com.task.zari.dto.request.board.BoardHeartListRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.board.BoardResponseDto;
import com.task.zari.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyRole('LESSOR', 'REALTOR', 'LESSEE')")
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @DeleteMapping("/v1/board/{id}")
    public ResponseEntity<ResponseDto> communityDelete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(boardService.boardDelete(id));
    }

    @GetMapping("/v1/board/heart")
    public ResponseEntity<SingleResponseDto<Page<BoardResponseDto>>> boardHeartList(@ModelAttribute BoardHeartListRequestDto dto) {
        return ResponseEntity.ok().body(boardService.boardHeartList(dto));
    }
}
