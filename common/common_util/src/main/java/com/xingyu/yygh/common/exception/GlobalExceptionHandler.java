package com.xingyu.yygh.common.exception;

import com.xingyu.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(YyghException.class)
    public Result error(YyghException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
