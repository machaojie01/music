package com.mcj.music.dao;

import com.mcj.music.domain.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:06
 * @email 2037612492@qq.com
 * @description 评价DAO
 */
@Mapper
@Repository
public interface RankMapper {

    /**
     * 添加评价
     * @param rank
     * @return
     */
    public int insert(Rank rank);

    /**
     * 查询总分
     * @param songListId
     * @return
     */
    public int selectScoreSum(@RequestParam("songListId") Integer songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    public int selectRankNum(@RequestParam("songListId") Integer songListId);
}
