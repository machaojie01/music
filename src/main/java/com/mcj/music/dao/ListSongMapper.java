package com.mcj.music.dao;

import com.mcj.music.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:06
 * @email 2037612492@qq.com
 * @description 歌单歌曲DAO
 */
@Mapper
@Repository
public interface ListSongMapper {

    /**
     * 添加歌单歌曲
     * @param listSong
     * @return
     */
    public int insert(ListSong listSong);

    /**
     * 修改歌单歌曲
     * @param listSong
     * @return
     */
    public int update(ListSong listSong);

    /**
     * 删除歌单歌曲
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据歌曲id和歌单id进行删除
     * @param songId
     * @param songListId
     * @return
     */
    public int deleteBySongIdAndSongListId(@Param("songId") Integer songId, @Param("songListId") Integer songListId);

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
