package com.task.zari.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtils { //요청, 응답 관련 유틸

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    //JSON Response
    public static <T> void jsonErrorResponse(int status, ResponseResult result, HttpServletResponse response, Exception e) throws IOException {
        logger.error(e.getMessage());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(new ResponseDto(result));
        response.getWriter().write(json);
        response.setStatus(status);
    }

    
    //ResponseEntity 생성
    public static ResponseEntity<ResponseDto> getResponseEntity(HttpStatus status, ResponseResult result, Exception e) {
        logger.error(e.getMessage());
        return getResponseEntity(status, result);
    }
    
    public static ResponseEntity<ResponseDto> getResponseEntity(HttpStatus status, ResponseResult result) {
        return ResponseEntity.status(status)
                .body(new ResponseDto(result));
    }

    //인증 헤더에서 계정 아이디 가져오기
    public static String getUserId() {
        String header = getRequest().getHeader("Authentication");
        if(header==null) return null;
        String[] split = header.split(" ");
        return split.length==2?split[1]:null;
    }


}
