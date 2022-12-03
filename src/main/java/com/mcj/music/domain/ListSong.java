package com.mcj.music.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author mcj
 * @date 2022/10/16 11:00
 * @email 2037612492@qq.com
 * @description 歌单歌曲
 */
@ApiModel("歌单歌曲")
public class ListSong implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 歌曲id
     */
    @ApiModelProperty("歌曲id")
    private Integer songId;

    /**
     * 歌单id
     */
    @ApiModelProperty("歌单id")
    private Integer songListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }
}
