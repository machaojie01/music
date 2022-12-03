package com.mcj.music.domain;

import com.mcj.music.annotation.EmailFormatCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author mcj
 * @date 2022/10/9 9:34
 * @email 2037612492@qq.com
 * @description 管理员
 */
@ApiModel("管理员")
public class Admin implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电子邮箱")
    @EmailFormatCheck
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
