package com.task.zari.controller.account;

import com.task.zari.dto.request.account.AccountSaveRequestDto;
import com.task.zari.dto.request.account.LoginRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.account.LoginResponseDto;
import com.task.zari.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasAnyRole('LESSOR', 'REALTOR', 'LESSEE', 'USER')")
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/v1/account")
    public ResponseEntity<ResponseDto> accountSave(@Valid @RequestBody AccountSaveRequestDto dto) {
        return ResponseEntity.ok(accountService.accountSave(dto));
    }

    @PostMapping("/v1/login")
    public ResponseEntity<SingleResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(accountService.login(dto));
    }

    @DeleteMapping("/v1/account")
    public ResponseEntity<ResponseDto> accountDelete(@RequestHeader("password") String password) throws Exception{
        return ResponseEntity.ok(accountService.accountDelete(password));
    }


}
