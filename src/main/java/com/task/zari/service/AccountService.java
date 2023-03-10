package com.task.zari.service;

import com.task.zari.dto.request.account.AccountSaveRequestDto;
import com.task.zari.dto.request.account.LoginRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.account.LoginResponseDto;
import com.task.zari.entity.account.Account;
import com.task.zari.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    public ResponseDto accountSave(AccountSaveRequestDto dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Account account = new Account(dto);
        accountRepository.save(account);
        return new ResponseDto(ResponseResult.SUCCESS);
    }

    public SingleResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getAccount_id(), dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return null;
    }
}
