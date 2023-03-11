package com.task.zari.service.board;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.entity.board.Board;
import com.task.zari.exception.NoDataException;
import com.task.zari.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public ResponseDto boardDelete(Long id) throws Exception { //게시글 삭제
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoDataException("No Data"));
        board.delete();
        return new ResponseDto(ResponseResult.SUCCESS);
    }
}
