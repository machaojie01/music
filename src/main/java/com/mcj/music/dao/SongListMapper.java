package com.mcj.music.dao;

import com.mcj.music.domain.Song;
import com.mcj.music.domain.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:06
 * @email 2037612492@qq.com
 * @description 歌单DAO
 */
@Mapper
@Repository
public interface SongListMapper {

    /**
     * 添加歌曲
     * @param songList
     * @return
     */
    public int insert(SongList songList);

    /**
     * 修改歌曲
     * @param songList
     * @return
     */
    public int update(SongList songList);

    /**
     * 删除歌曲
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据逐渐查询整个对象
     * @param id
     * @return
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     * @return
     */
    public List<SongList> allSongList();

    /**
     * 根据标题模糊查询歌单列表
     * @param title
     * @return
     */
    public List<SongList> songListLikeTitle(String title);

    /**
     * 根据标题精确查询歌单列表
     * @param title
     * @return
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     * @param style
     * @return
     */
    public List<SongList> songListLikeStyle(String style);

}
