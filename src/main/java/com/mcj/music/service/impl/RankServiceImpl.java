package com.mcj.music.service.impl;

import com.mcj.music.dao.RankMapper;
import com.mcj.music.domain.Rank;
import com.mcj.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mcj
 * @date 2022/10/18 8:01
 * @email 2037612492@qq.com
 * @description 评价service实现类
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 添加评价
     *
     * @param rank
     * @return
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank) > 0;
    }

    /**
     * 查询总分
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查总评分人数
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId
     * @return
     */
    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = rankMapper.selectRankNum(songListId);
        if (rankNum == 0) {
            return 5;
        }
        return rankMapper.selectScoreSum(songListId) / rankNum;
    }
}
