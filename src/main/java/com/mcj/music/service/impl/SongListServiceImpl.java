package com.mcj.music.service.impl;

import com.mcj.music.dao.SongListMapper;
import com.mcj.music.domain.SongList;
import com.mcj.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/16 9:58
 * @email 2037612492@qq.com
 * @description 歌单service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 添加歌曲
     *
     * @param songList
     * @return
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * 修改歌曲
     *
     * @param songList
     * @return
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList) > 0;
    }

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id) > 0;
    }

    /**
     * 根据逐渐查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     *
     * @return
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListLikeTitle(String title) {
        return songListMapper.songListLikeTitle("%" + title + "%");
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> songListLikeStyle(String style) {
        return songListMapper.songListLikeStyle("%" + style + "%");
    }
}
