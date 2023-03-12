package com.task.zari.advice;

import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import com.task.zari.util.HttpUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.task.zari.controller.board.heart")
public class HeartExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto> heartDuplicate(Exception e) { //같은글에 좋아요를 두번이상 등록하는 경우
        return HttpUtils.getResponseEntity(HttpStatus.CONFLICT, ResponseResult.HEART_DUPLICATE, e);
    }

}
