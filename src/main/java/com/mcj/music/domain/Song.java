package com.mcj.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mcj
 * @date 2022/10/11 20:28
 * @email 2037612492@qq.com
 * @description 歌曲
 */
@ApiModel("歌曲")
public class Song implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 歌手id
     */
    @ApiModelProperty("歌手id")
    private Integer singerId;

    /**
     * 歌名
     */
    @ApiModelProperty("歌名")
    private String name;

    /**
     * 介绍
     */
    @ApiModelProperty("介绍")
    private String introduction;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建事件")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String pic;


    /**
     * 歌词
     */
    @ApiModelProperty("歌词")
    private String lyric;

    /**
     * 歌曲地址
     */
    @ApiModelProperty("歌曲地址")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
