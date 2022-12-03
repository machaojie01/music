package com.mcj.music.service;

import com.mcj.music.domain.ListSong;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/16 11:19
 * @email 2037612492@qq.com
 * @description 歌单歌曲service
 */
public interface ListSongService {

    /**
     * 添加歌单歌曲
     * @param listSong
     * @return
     */
    public boolean insert(ListSong listSong);

    /**
     * 修改歌单歌曲
     * @param listSong
     * @return
     */
    public boolean update(ListSong listSong);

    /**
     * 删除歌单歌曲
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据歌曲id和歌单id进行删除
     * @param songId
     * @param songListId
     * @return
     */
    public boolean deleteBySongIdAndSongListId(@Param("songId") Integer songId, @Param("songListId") Integer songListId);


    /**
     * 根据逐渐查询整个对象
     * @param id
     * @return
     */
    public ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单歌曲
     * @return
     */
    public List<ListSong> allListSong();

    /**
     * 根据歌单id查询所有的歌曲
     * @param songListId
     * @return
     */
    public List<ListSong> listSongOfSongListId(Integer songListId);

}
