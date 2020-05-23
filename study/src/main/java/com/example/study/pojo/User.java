package com.example.study.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author libo
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BasePojo implements Serializable {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
}
