package com.mcj.music.dao;

import com.mcj.music.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/11 21:04
 * @email 2037612492@qq.com
 * @description 收藏dao
 */
@Mapper
@Repository
public interface CollectMapper {

    /**
     * 增加
     * @param collect
     * @return
     */
    int insert(Collect collect);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 查询所有收藏
     * @return
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     * @param userId
     * @return
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     * @param userId
     * @param songId
     * @return
     */
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);


}
