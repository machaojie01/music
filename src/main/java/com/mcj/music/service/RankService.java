package com.mcj.music.service;

import com.mcj.music.domain.Rank;

/**
 * @author mcj
 * @date 2022/10/18 7:59
 * @email 2037612492@qq.com
 * @description 评价service
 */
public interface RankService {

    /**
     * 添加评价
     * @param rank
     * @return
     */
    public boolean insert(Rank rank);

    /**
     * 查询总分
     * @param songListId
     * @return
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     * @param songListId
     * @return
     */
    public int rankOfSongListId(Integer songListId);

}
