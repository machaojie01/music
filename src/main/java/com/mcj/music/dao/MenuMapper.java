package com.mcj.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mcj
 * @date 2022/11/20 13:50
 * @email 2037612492@qq.com
 * @description 权限相关mapper
 */
@Mapper
@Repository
public interface MenuMapper {

    List<String> selectPermsByUserId(@Param("userId") Integer userId);

}
