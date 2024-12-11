package com.mmall.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        // 打印错误日志
        ex.printStackTrace();

        // 返回通用错误响应
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.BUSINESS_ERROR.getCode());
        response.put("msg", ResponseCode.BUSINESS_ERROR.getDesc());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException ex) {
        // 数据库错误的专属处理
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.DATABASE_ERROR.getCode());
        response.put("msg", ResponseCode.DATABASE_ERROR.getDesc());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
