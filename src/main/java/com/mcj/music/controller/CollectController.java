package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Collect;
import com.mcj.music.service.CollectService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mcj
 * @date 2022/10/12 20:15
 * @email 2037612492@qq.com
 * @description 收藏控制类
 */
@Api(value = "收藏控制类", tags = "和收藏有关的一些接口")
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    @ApiOperation(value = "添加收藏", notes = "添加收藏")
    @PostMapping("/add")
    public ResponseUtils addCollect(@RequestBody Collect collect) {
        ResponseUtils responseUtils = new ResponseUtils();
        if (collect.getType() != null) {
            if (collect.getType() == 0 && collect.getSongId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌曲id不能为空");
                return responseUtils;
            }
            if (collect.getType() == 1 && collect.getSongListId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌单id不能为空");
                return responseUtils;
            }
        }
        boolean flag = collectService.insert(collect);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }


    /**
     * 删除收藏
     * @param id
     * @return
     */
    @ApiOperation(value = "删除收藏", notes = "删除收藏")
    @PostMapping("/delete")
    public ResponseUtils deleteCollect(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        boolean flag = collectService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }

    /**
     * 查询所有收藏
     * @return
     */
    @ApiOperation(value = "查询所有收藏", notes = "查询所有收藏")
    @PostMapping("/all")
    public ResponseUtils allCollect() {
        return ResponseUtils.success("查询成功", collectService.allCollect());
    }

    /**
     * 查询某个用户的收藏列表
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询某个用户的收藏列表", notes = "查询某个用户的收藏列表")
    @PostMapping("/user/id")
    public ResponseUtils collectOfUserId(@RequestParam("userId") Integer userId) {
        return ResponseUtils.success("查询成功", collectService.collectOfUserId(userId));
    }

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     * @param userId
     * @param songId
     * @return
     */
    @ApiOperation(value = "查询某个用户是否已经收藏了某个歌曲", notes = "查询某个用户是否已经收藏了某个歌曲")
    @PostMapping("/exist/song/id")
    public ResponseUtils existSongId(@RequestParam("userId") Integer userId, @RequestParam("songId") Integer songId) {
        return ResponseUtils.success("查询成功", collectService.existSongId(userId, songId));
    }



}
