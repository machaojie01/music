package com.mcj.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mcj
 * @date 2022/10/13 21:17
 * @email 2037612492@qq.com
 * @description 静态资源重定位配置类
 */
@Configuration
public class StaticResourceRelocationConfig implements WebMvcConfigurer {

    /**
     * 1.System.getProperty("user.dir")获取当前项目的根目录，在这个项目中就是 D:\Software\work\IDEA\IdeaProject\WebDemo\music
     * 2.System.getProperty("file.separator")获取文件分隔符，主要是为了防止不同的系统其文件分割符不同
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 歌手图片地址的定位
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "singerPic" + System.getProperty("file.separator")
        );
        // 歌曲图片地址的定位
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "songPic" + System.getProperty("file.separator")
        );
        // 歌单图片地址的定位
        registry.addResourceHandler("/img/songListPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator")
        );
        // 歌曲地址的定位
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
                        + System.getProperty("file.separator")
        );
        // 前端用户头像的定位
        registry.addResourceHandler("/avatorImages/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages"
                        + System.getProperty("file.separator")
        );
    }
}
