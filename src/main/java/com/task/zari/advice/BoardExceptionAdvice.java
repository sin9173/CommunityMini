package com.task.zari.advice;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.util.HttpUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.task.zari.controller.board")
public class BoardExceptionAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDto> badCredential(Exception e) { //인증정보가 잘못되었을 경우
        return HttpUtils.getResponseEntity(HttpStatus.UNAUTHORIZED, ResponseResult.NOT_WRITE_ACCOUNT, e);
    }
}
