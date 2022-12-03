package com.mcj.music.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * @author mcj
 * @date 2022/10/17 20:35
 * @email 2037612492@qq.com
 * @description 评价
 */
@ApiModel("评价")
public class Rank implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 歌单id
     */
    @ApiModelProperty("歌单id")
    private Integer songListId;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer consumerId;

    /**
     * 评分
     */
    @ApiModelProperty("评分")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
