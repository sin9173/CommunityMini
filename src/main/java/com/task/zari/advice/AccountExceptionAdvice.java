package com.task.zari.advice;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.task.zari.controller.account")
public class AccountExceptionAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseDto> duplicate(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseDto(ResponseResult.ACCOUNT_DUPLICATE));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ResponseDto> notValid(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(ResponseResult.NOT_VALID));
    }
}
