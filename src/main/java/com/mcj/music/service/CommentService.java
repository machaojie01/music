package com.mcj.music.service;

import com.mcj.music.domain.Comment;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/19 8:22
 * @email 2037612492@qq.com
 * @description 评论Service
 */
public interface CommentService {

    /**
     * 增加
     * @param comment
     * @return
     */
    public boolean insert(Comment comment);

    /**
     * 修改
     * @param comment
     * @return
     */
    public boolean update(Comment comment);

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
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     * @return
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     * @param songId
     * @return
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     * @param songListId
     * @return
     */
    public List<Comment> commentOfSongListId(Integer songListId);
    
}
