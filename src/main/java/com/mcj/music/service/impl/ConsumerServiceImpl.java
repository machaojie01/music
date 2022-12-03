package com.mcj.music.service.impl;

import com.mcj.music.dao.ConsumerMapper;
import com.mcj.music.domain.Consumer;
import com.mcj.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mcj
 * @date 2022/10/16 17:08
 * @email 2037612492@qq.com
 * @description 前端用户service实现类
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 增加
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer) > 0;
    }

    /**
     * 修改
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerMapper.allConsumer();
    }

    /**
     * 验证密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username, password);
    }

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    @Override
    public Consumer getByUsername(String username) {
        return consumerMapper.getByUsername(username);
    }
}
