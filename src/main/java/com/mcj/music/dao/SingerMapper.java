package com.mcj.music.dao;

import com.mcj.music.domain.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/11 21:04
 * @email 2037612492@qq.com
 * @description 歌手dao
 */
@Mapper
@Repository
public interface SingerMapper {

    /**
     * 增加
     * @param singer
     * @return
     */
    public int insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    public int update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

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
