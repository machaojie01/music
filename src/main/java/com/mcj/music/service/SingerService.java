package com.mcj.music.service;

import com.mcj.music.domain.Singer;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/12 20:10
 * @email 2037612492@qq.com
 * @description 歌手service接口
 */
public interface SingerService {

    /**
     * 增加
     * @param singer
     * @return
     */
    public boolean insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    public boolean update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     * @return
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名模糊查询列表
     * @param name
     * @return
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     * @param sex
     * @return
     */
    public List<Singer> singerOfSex(Integer sex);

}
