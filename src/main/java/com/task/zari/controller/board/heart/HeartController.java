package com.task.zari.controller.board.heart;

import com.task.zari.dto.request.board.heart.HeartSaveRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.service.board.heart.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyRole('LESSOR', 'REALTOR', 'LESSEE')")
@RequestMapping("/api")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/v1/heart")
    public ResponseEntity<ResponseDto> heartSave(@RequestBody HeartSaveRequestDto dto) throws Exception {
        return ResponseEntity.ok().body(heartService.heartSave(dto));
    }
}
