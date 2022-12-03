package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.ListSong;
import com.mcj.music.service.ListSongService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mcj
 * @date 2022/10/16 11:24
 * @email 2037612492@qq.com
 * @description 歌单歌曲controller
 */
@RestController
@RequestMapping("/list/song")
@Api(value = "歌单歌曲controller", tags = "歌单歌曲有关的所有接口")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     * @param listSong
     * @return
     */
    @ApiOperation(value = "给歌单添加歌曲", notes = "给歌单添加歌曲")
    @PostMapping("/add")
    public ResponseUtils addListSong(@RequestBody ListSong listSong) {
        ResponseUtils responseUtils;
        boolean flag = listSongService.insert(listSong);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }

    /**
     * 根据歌单id查询
     * @param songListId
     * @return
     */
    @ApiOperation(value = "根据歌单id查询", notes = "根据歌单id查询")
    @PostMapping("/detail/id")
    public ResponseUtils listSongOfSongListId(@RequestParam("songListId") Integer songListId) {
        return ResponseUtils.success("查询成功", listSongService.listSongOfSongListId(songListId));
    }

    /**
     * 删除歌单里的歌曲
     * @param id
     * @return
     */
    @ApiOperation(value = "删除歌单里的歌曲", notes = "删除歌单里的歌曲")
    @PostMapping("/delete")
    public ResponseUtils deleteListSong(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        boolean flag = listSongService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }

    /**
     * 根据歌曲id与歌单id删除数据
     * @param songId
     * @param songListId
     * @return
     */
    @ApiOperation(value = "根据歌曲id与歌单id删除记录", tags = "根据歌曲id与歌单id删除数据")
    @PostMapping("/delete/song/id")
    public ResponseUtils deleteBySongIdAndSongListId(@RequestParam("songId") Integer songId, @RequestParam("songListId") Integer songListId) {
        ResponseUtils responseUtils;
        boolean flag = listSongService.deleteBySongIdAndSongListId(songId, songListId);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }


}
