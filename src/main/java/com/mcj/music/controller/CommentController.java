package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Comment;
import com.mcj.music.service.CommentService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mcj
 * @date 2022/10/12 20:15
 * @email 2037612492@qq.com
 * @description 评论控制类
 */
@Api(value = "评论控制类", tags = "和评论有关的一些接口")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping("/add")
    public ResponseUtils addComment(@RequestBody Comment comment) {
        ResponseUtils responseUtils = new ResponseUtils();
        comment.setUp(0);
        if (comment.getType() != null) {
            if (comment.getType() == 0 && comment.getSongId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌曲id不能为空");
                return responseUtils;
            }
            if (comment.getType() == 1 && comment.getSongListId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌单id不能为空");
                return responseUtils;
            }
        }
        boolean flag = commentService.insert(comment);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @ApiOperation(value = "修改评论", notes = "修改评论")
    @PostMapping("/update")
    public ResponseUtils updateComment(@RequestBody Comment comment) {
        ResponseUtils responseUtils = new ResponseUtils();
        if (comment.getType() != null) {
            if (comment.getType() == 0 && comment.getSongId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌曲id不能为空");
                return responseUtils;
            }
            if (comment.getType() == 1 && comment.getSongListId() == null) {
                responseUtils.setCode(500);
                responseUtils.setMsg("歌单id不能为空");
                return responseUtils;
            }
        }
        boolean flag = commentService.update(comment);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("修改成功");
        } else {
            responseUtils = ResponseUtils.fail("修改失败");
        }
        return responseUtils;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @ApiOperation(value = "删除评论", notes = "删除评论")
    @PostMapping("/delete")
    public ResponseUtils deleteComment(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        boolean flag = commentService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }

    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询评论", notes = "根据id查询评论")
    @PostMapping("/select/id")
    public ResponseUtils selectByPrimaryKey(@RequestParam("id") Integer id) {
        return ResponseUtils.success("查询成功" ,commentService.selectByPrimaryKey(id));
    }

    /**
     * 查询所有评论
     * @return
     */
    @ApiOperation(value = "查询所有评论", notes = "查询所有评论")
    @PostMapping("/all")
    public ResponseUtils allComment() {
        return ResponseUtils.success("查询成功", commentService.allComment());
    }

    /**
     * 查询某个歌曲下的所有评论
     * @param songId
     * @return
     */
    @ApiOperation(value = "查询某个歌曲下的所有评论", notes = "查询某个歌曲下的所有评论")
    @PostMapping("/select/song")
    public ResponseUtils commentOfSongId(@RequestParam("songId") Integer songId) {
        return ResponseUtils.success("查询成功", commentService.commentOfSongId(songId));
    }

    /**
     * 查询某个歌单下的所有评论
     * @param songListId
     * @return
     */
    @ApiOperation(value = "查询某个歌单下的所有评论", notes = "查询某个歌单下的所有评论")
    @PostMapping("/select/song/list")
    public ResponseUtils commentOfSongListId(@RequestParam("songListId") Integer songListId) {
        return ResponseUtils.success("查询成功", commentService.commentOfSongListId(songListId));
    }

    /**
     * 给某个评论点赞
     * @param id
     * @return
     */
    @ApiOperation(value = "给某个评论点赞", notes = "给某个评论点赞")
    @PostMapping("/like")
    public ResponseUtils like(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        JSONObject jsonObject = new JSONObject();
        Comment comment = commentService.selectByPrimaryKey(id);
        comment.setUp(comment.getUp() + 1);
        boolean flag = commentService.update(comment);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("点赞成功");
        } else {
            responseUtils = ResponseUtils.fail("点赞失败");
        }
        return responseUtils;
    }


}
