package com.mcj.music.service;

import com.mcj.music.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/20 8:11
 * @email 2037612492@qq.com
 * @description 收藏service
 */
public interface CollectService {

    /**
     * 增加
     * @param collect
     * @return
     */
    boolean insert(Collect collect);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

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
    boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

}
