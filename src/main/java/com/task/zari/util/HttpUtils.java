package com.task.zari.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static <T> void jsonErrorResponse(int status, ResponseResult result, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(new ResponseDto(result));
        response.getWriter().write(json);
        response.setStatus(status);
    }

    public static ResponseEntity<ResponseDto> getResponseEntity(HttpStatus status, ResponseResult result) {
        return ResponseEntity.status(status)
                .body(new ResponseDto(result));
    }

    public static String getUserId() {
        String header = getRequest().getHeader("Authentication");
        if(header==null) return null;
        String[] split = header.split(" ");
        return split.length==2?split[1]:null;
    }


}
