package com.mcj.music.dao;

import com.mcj.music.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:06
 * @email 2037612492@qq.com
 * @description 歌曲DAO
 */
@Mapper
@Repository
public interface SongMapper {

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    public int insert(Song song);

    /**
     * 修改歌曲
     * @param song
     * @return
     */
    public int update(Song song);

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
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    public List<Song> allSong();

    /**
     * 根据歌名模糊查询列表
     * @param name
     * @return
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌手id查询列表
     * @param singerId
     * @return
     */
    public List<Song> songOfSingerId(Integer singerId);

}
