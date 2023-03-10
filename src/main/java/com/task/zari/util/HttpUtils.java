package com.task.zari.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.zari.dto.response.ResponseDto;
import com.task.zari.dto.response.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtils {

    public static <T> void jsonErrorResponse(int status, ResponseResult result, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(new ResponseDto(result));
        response.getWriter().write(json);
        response.setStatus(status);
    }
}
