package com.mcj.music.service.impl;

import com.mcj.music.dao.ListSongMapper;
import com.mcj.music.domain.ListSong;
import com.mcj.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/16 11:20
 * @email 2037612492@qq.com
 * @description 歌单歌曲service实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 添加歌单歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    /**
     * 修改歌单歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong) > 0;
    }

    /**
     * 删除歌单歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id) > 0;
    }

    /**
     * 根据歌曲id和歌单id进行删除
     *
     * @param songId
     * @param songListId
     * @return
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndSongListId(songId, songListId) > 0;
    }

    /**
     * 根据逐渐查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据歌单id查询所有的歌曲
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId );
    }
}
