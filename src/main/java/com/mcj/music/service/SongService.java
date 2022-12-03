package com.mcj.music.service;

import com.mcj.music.domain.Song;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:29
 * @email 2037612492@qq.com
 * @description 歌曲service
 */
public interface SongService {

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    public boolean insert(Song song);

    /**
     * 修改歌曲
     * @param song
     * @return
     */
    public boolean update(Song song);

    /**
     * 删除歌曲
     * @param id
     * @return
     */
    public boolean delete(Integer id);

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
     * 根据歌名精确查询列表
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
