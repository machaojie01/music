package com.mcj.music.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author mcj
 * @date 2022/10/16 9:38
 * @email 2037612492@qq.com
 * @description 歌单
 */
@ApiModel("歌单")
public class SongList implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 歌单标题
     */
    @ApiModelProperty("歌单标题")
    private String title;

    /**
     * 歌单图片
     */
    @ApiModelProperty("歌单图片")
    private String pic;

    /**
     * 歌单简介
     */
    @ApiModelProperty("歌单简介")
    private String introduction;

    /**
     * 风格
     */
    @ApiModelProperty("风格")
    private String style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
