package com.mcj.music.service;

import com.mcj.music.domain.Consumer;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/16 17:07
 * @email 2037612492@qq.com
 * @description 前端用户Service
 */
public interface ConsumerService {

    /**
     * 增加
     * @param consumer
     * @return
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     * @return
     */
    public boolean update(Consumer consumer);

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
    public Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    public List<Consumer> allConsumer();

    /**
     * 验证密码
     * @param username
     * @param password
     * @return
     */
    public int verifyPassword(String username, String password);

    /**
     * 根据账号查询
     * @return
     */
    public Consumer getByUsername(String username);

}
