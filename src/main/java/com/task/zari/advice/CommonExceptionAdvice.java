package com.task.zari.advice;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.exception.NoAccountException;
import com.task.zari.exception.NoDataException;
import com.task.zari.util.HttpUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.task.zari.controller")
public class CommonExceptionAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto> dataIntegrity(Exception e) {
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.DATA_INTEGRITY);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseDto> missingHeader(Exception e) {
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.NOT_VALID);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ResponseDto> noData(Exception e) { //데이터가 존재하지 않을 경우
        return HttpUtils.getResponseEntity(HttpStatus.NOT_FOUND, ResponseResult.DATA_NOT_FOUND);
    }

    @ExceptionHandler(NoAccountException.class)
    public ResponseEntity<ResponseDto> noAccount(Exception e) { //계정이 존재하지 않을 경우
        return HttpUtils.getResponseEntity(HttpStatus.FORBIDDEN, ResponseResult.USERNAME_NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> noValid(Exception e) {
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.NOT_VALID);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> missingData(Exception e) {
        return HttpUtils.getResponseEntity(HttpStatus.BAD_REQUEST, ResponseResult.NOT_VALID);
    }


}
