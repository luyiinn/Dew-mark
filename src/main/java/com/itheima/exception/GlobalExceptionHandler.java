package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice   // 表示定义了一个全局异常处理器
public class GlobalExceptionHandler {

    // 指定要捕获的异常
    @ExceptionHandler(Exception.class) // 捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace(); // 输出异常的堆栈信息
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
