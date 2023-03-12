package com.task.zari.advice;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.exception.NoDataException;
import com.task.zari.util.HttpUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.task.zari.controller.account")
public class AccountExceptionAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseDto> duplicate(Exception e) { // DB에 중복된 데이터가 입력되었을 경우
        return HttpUtils.getResponseEntity(HttpStatus.CONFLICT, ResponseResult.ACCOUNT_DUPLICATE, e);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ResponseDto> notValid(Exception e) { // 요청 파라미터가 없거나 유효하지 않은 경우
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.NOT_VALID, e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> notValidParse(Exception e) { //Json 을 객체로 파싱할 때에 문제가 발생하는 경우
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.NOT_VALID, e);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDto> badCredential(Exception e) { //인증정보가 잘못되었을 경우
        return HttpUtils.getResponseEntity(HttpStatus.UNAUTHORIZED, ResponseResult.BAD_CREDENTIAL, e);
    }

}
