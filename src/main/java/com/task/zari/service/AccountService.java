package com.task.zari.service;

import com.task.zari.auth.PrincipalDetails;
import com.task.zari.dto.request.account.AccountSaveRequestDto;
import com.task.zari.dto.request.account.LoginRequestDto;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.dto.response.SingleResponseDto;
import com.task.zari.dto.response.account.LoginResponseDto;
import com.task.zari.entity.account.Account;
import com.task.zari.exception.NoAccountException;
import com.task.zari.exception.NoDataException;
import com.task.zari.repository.account.AccountRepository;
import com.task.zari.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    public ResponseDto accountSave(AccountSaveRequestDto dto) { //계정등록
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Account account = new Account(dto);
        accountRepository.save(account);
        return new ResponseDto(ResponseResult.SUCCESS);
    }

    public SingleResponseDto<LoginResponseDto> login(LoginRequestDto dto) { //로그인
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getAccount_id(), dto.getPassword());

        //loadUserByUsername 이 실행
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //SecurityContext 에 Authentication 객체를 넣어 인증
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponseDto response =
                new LoginResponseDto(((PrincipalDetails) authentication.getPrincipal()).getAuthDto());

        return new SingleResponseDto<>(ResponseResult.SUCCESS, response);
    }

    @Transactional
    public ResponseDto accountDelete(String password) throws Exception { //비밀번호 변경
        Account account = accountRepository.findByAccountId(HttpUtils.getUserId())
                .orElseThrow(() -> new NoAccountException("No Account"));
        if(!bCryptPasswordEncoder.matches(password, account.getPassword()))
            throw new BadCredentialsException("Invalid Password");
        account.delete();
        return new ResponseDto(ResponseResult.SUCCESS);
    }


}
