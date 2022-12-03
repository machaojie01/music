package com.mcj.music.dao;

import com.mcj.music.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mcj
 * @date 2022/10/9 9:42
 * @email 2037612492@qq.com
 * @description 管理员Dao
 */
@Mapper
@Repository
public interface AdminMapper {

    /**
     * 验证用户名密码是否正确
     */
    int verifyPassword(@Param("username") String username, @Param("password") String password);

    Admin queryUserByUsername(@Param("username") String username);

}
