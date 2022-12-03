package com.mcj.music.domain;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author mcj
 * @date 2022/11/20 13:47
 * @email 2037612492@qq.com
 * @description 权限表
 */
@Data
public class Menu {

    /**
     * id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 路由
     */
    private String path;

    /**
     * 权限
     */
    private String perms;

    /**
     * 状态 0：启用，1：停用
     */
    private Integer status;

}
