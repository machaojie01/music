package com.mcj.music.service;

import com.mcj.music.utils.ResponseUtils;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author mcj
 * @date 2022/10/9 10:26
 * @email 2037612492@qq.com
 * @description 管理员service接口
 */
public interface AdminService {

    /**
     * 验证用户名密码是否正确
     */
    Map<String, String> verifyPassword(String username, String password);

    ResponseUtils logout();
}
