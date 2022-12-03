package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Rank;
import com.mcj.music.service.RankService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author mcj
 * @date 2022/10/9 10:39
 * @email 2037612492@qq.com
 * @description 评价controller
 */
@RestController
@Api(value = "评价控制类", tags = "与评价相关的接口")
@RequestMapping("rank")
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 添加评价
     * @param rank
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加评价", notes = "添加评价")
    public ResponseUtils loginStatus(@RequestBody Rank rank) {
        ResponseUtils responseUtils;
        boolean flag = rankService.insert(rank);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("评价啊成功");
        } else {
            responseUtils = ResponseUtils.fail("评价失败");
        }
        return responseUtils;
    }

    /**
     * 计算平均分
     * @param songListId
     * @return
     */
    @PostMapping("/avg")
    @ApiOperation(value = "计算平均分", notes = "计算平均分")
    public ResponseUtils avgScore(@RequestParam("songListId") Integer songListId) {
        return ResponseUtils.success("计算成功", rankService.rankOfSongListId(songListId));
    }

}
