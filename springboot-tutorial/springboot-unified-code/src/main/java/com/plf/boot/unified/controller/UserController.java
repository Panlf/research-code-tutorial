package com.plf.boot.unified.controller;

import com.plf.boot.unified.bean.User;
import com.plf.boot.unified.bean.group.InsertUserGroup;
import com.plf.boot.unified.bean.group.UpdateUserGroup;
import com.plf.boot.unified.common.bean.R;
import com.plf.boot.unified.common.exception.ParamNotValidException;
import com.plf.boot.unified.common.exception.UserNotFoundException;
import com.plf.boot.unified.enums.ResultCodeEnum;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("unified")
public class UserController {

    @GetMapping("get")
    public R getUser(long id){
        if(id==10){
            throw new UserNotFoundException(ResultCodeEnum.USER_NOT_FOUND.getCode(),"UserController.getUser()请求ID为"+id+"时反生找不到用户");
        }
        return R.success("返回成功");
    }

    @GetMapping("update")
    public R updateUser(@Validated({UpdateUserGroup.class}) User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamNotValidException(ResultCodeEnum.PARAM_ERROR.getCode(),"UserController.updateUser参数异常");
        }
        return R.success("返回成功");
    }

    @GetMapping("insert")
    public R insertUser(@Validated({InsertUserGroup.class})  User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamNotValidException(ResultCodeEnum.PARAM_ERROR.getCode(),"UserController.insertUser参数异常");
        }
        return R.success("返回成功");
    }

}
