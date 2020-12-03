package com.plf.boot.unified.global;

import com.plf.boot.unified.common.bean.R;
import com.plf.boot.unified.common.exception.ParamNotValidException;
import com.plf.boot.unified.common.exception.UserNotFoundException;
import com.plf.boot.unified.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R ExceptionHandler(Exception e){
        return R.error(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public R UserNotFoundExceptionHandler(UserNotFoundException e){
        log.error(e.getMessage());
        return R.error(ResultCodeEnum.USER_NOT_FOUND,null);
    }

    @ExceptionHandler(ParamNotValidException.class)
    @ResponseBody
    public R ParamNotValidExceptionHandler(ParamNotValidException e){
        log.error(e.getMessage());
        return R.error(ResultCodeEnum.PARAM_ERROR,null);
    }

}
