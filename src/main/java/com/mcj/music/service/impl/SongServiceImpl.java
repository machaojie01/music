package com.mcj.music.service.impl;

import com.mcj.music.dao.SongMapper;
import com.mcj.music.domain.Song;
import com.mcj.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/15 10:31
 * @email 2037612492@qq.com
 * @description 歌手service实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    /**
     * 添加歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 修改歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song) > 0;
    }

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id) > 0;
    }

    /**
     * 根据逐渐查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     *
     * @return
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 根据歌名模糊查询列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName("%" + name + "%");
    }

    /**
     * 根据歌手id查询列表
     *
     * @param singerId
     * @return
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.songOfSingerId(singerId);
    }
}
