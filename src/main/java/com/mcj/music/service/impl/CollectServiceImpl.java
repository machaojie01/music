package com.mcj.music.service.impl;

import com.mcj.music.dao.CollectMapper;
import com.mcj.music.domain.Collect;
import com.mcj.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/20 8:14
 * @email 2037612492@qq.com
 * @description 收藏service实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 增加
     *
     * @param collect
     * @return
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id) > 0;
    }

    /**
     * 查询所有收藏
     *
     * @return
     */
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId, songId) > 0;
    }
}
