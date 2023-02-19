package com.plf.boot.unified.bean;

import com.plf.boot.unified.bean.group.InsertUserGroup;
import com.plf.boot.unified.bean.group.UpdateUserGroup;
import com.plf.boot.unified.customize.validator.Password;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "user实体类",description = "用户对象")
@Builder
public class User {

    /**
     * 如果是long默认是0，这个就是用户ID不能为空就失效
     */
    @NotNull(message = "用户ID不能为空",groups = {UpdateUserGroup.class})
    @ApiModelProperty(value = "主键ID",name = "id")
    private Long id;

    @ApiModelProperty(value = "真实姓名",name = "realName")
    private String realName;

    @ApiModelProperty(value = "用户名",name = "userName")
    @NotNull(message = "用户账号不能为空",groups = {InsertUserGroup.class})
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    private String userName;

    @ApiModelProperty(value = "密码",name = "password")
    @NotNull(message = "用户密码不能为空",groups = {InsertUserGroup.class})
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符",groups = {InsertUserGroup.class})
    @Password(message = "用户密码不能为纯数字",groups = {InsertUserGroup.class})
    private String password;
}
