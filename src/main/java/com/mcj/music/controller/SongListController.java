package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.SongList;
import com.mcj.music.service.SongListService;
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

/**
 * @author mcj
 * @date 2022/10/16 10:02
 * @email 2037612492@qq.com
 * @description 歌单controller
 */
@RestController
@RequestMapping("/song/list")
@Api(value = "歌单controller", tags = "与歌单有关的一些接口")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     * @param songList
     * @return
     */
    @ApiOperation(value = "添加歌单", notes = "添加歌单")
    @PostMapping("/add")
    public ResponseUtils addSong(@RequestBody SongList songList, @RequestParam("pic") MultipartFile mpFile) {
        ResponseUtils responseUtils = new ResponseUtils();
        // 上传歌单图片
        if (mpFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("歌单上传失败");
            return responseUtils;
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeUrlPath = "img/songListPic/" + fileName;
        try {
            mpFile.transferTo(dest);
            songList.setPic(storeUrlPath);
            boolean flag = songListService.insert(songList);
            if (flag) {
                responseUtils.setCode(200);
                responseUtils.setMsg("保存成功");
                responseUtils.setData("song:" + storeUrlPath);
            } else {
                responseUtils.setCode(500);
                responseUtils.setMsg("保存失败");
            }
        } catch (IOException e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("保存失败");
            responseUtils.setData(e.getMessage());
        }
        return responseUtils;
    }

    /**
     * 修改歌单
     * @param songList
     * @return
     */
    @ApiOperation(value = "修改歌单", notes = "修改歌单")
    @PostMapping("/update")
    public ResponseUtils updateSong(@RequestBody SongList songList) {
        ResponseUtils responseUtils;
        boolean flag = songListService.update(songList);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("修改成功");
        } else {
            responseUtils = ResponseUtils.fail("修改失败");
        }
        return responseUtils;
    }

    /**
     * 删除歌单
     * @param id
     * @return
     */
    @ApiOperation(value = "删除歌单", notes = "删除歌单")
    @PostMapping("/delete")
    public ResponseUtils deleteSong(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        // 查询该歌单所在的位置，根据文件路径删除本地歌单图片
        SongList songList = songListService.selectByPrimaryKey(id);
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + songList.getPic();
        File file = new File(filePath);
        try {
            file.delete();
        } catch (Exception e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("删除失败");
            responseUtils.setData(e.getMessage());
            return responseUtils;
        }
        boolean flag = songListService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }

    /**
     * 更新歌单图片
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新歌单图片", notes = "更新歌单图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "歌单的图片", required = true),
            @ApiImplicitParam(name = "id", value = "歌单的id", required = true)
    })
    @PostMapping(value = "/update/pic")
    public ResponseUtils updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        if (avatorFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("文件上传失败");
            return responseUtils;
        }
        // 查询该歌单所在的位置，根据文件路径删除本地歌单
        SongList oldSongList = songListService.selectByPrimaryKey(id);
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + oldSongList.getPic();
        File oldFile = new File(path);
        try {
            oldFile.delete();
        } catch (Exception e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("旧图片删除失败");
            responseUtils.setData(e.getMessage());
            return responseUtils;
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeAvatorPath = "img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if (flag) {
                responseUtils.setCode(200);
                responseUtils.setMsg("上传成功");
                responseUtils.setData("pic:" + storeAvatorPath);
            } else {
                responseUtils.setCode(500);
                responseUtils.setMsg("上传失败");
            }
        } catch (IOException e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("上传失败");
            responseUtils.setData(e.getMessage());
        }
        return responseUtils;
    }

    /**
     * 根据主键查询歌单
     * @param id
     * @return
     */
    @ApiOperation(value = "根据主键查询歌单", notes = "根据主键查询歌单")
    @PostMapping("/select/key")
    public ResponseUtils selectByPrimaryKey(@RequestParam("id") Integer id) {
        return ResponseUtils.success("查询成功", songListService.selectByPrimaryKey(id));
    }

    /**
     * 查询所有歌单
     * @return
     */
    @ApiOperation(value = "查询所有歌单", notes = "查询所有歌单")
    @PostMapping("/select/all")
    public ResponseUtils allSongList() {
        return ResponseUtils.success("查询成功", songListService.allSongList());
    }

    /**
     * 根据title精确查询歌单
     * @param title
     * @return
     */
    @ApiOperation(value = "根据title精确查询歌单", notes = "根据title精确查询歌单")
    @PostMapping("/title/detail")
    public ResponseUtils songListOfTitle(@RequestParam("title") String title) {
        return ResponseUtils.success("查询成功", songListService.songListOfTitle(title));
    }

    /**
     * 根据title模糊查询歌单
     * @param title
     * @return
     */
    @ApiOperation(value = "根据title模糊查询歌单", notes = "根据title模糊查询歌单")
    @PostMapping("/title/like/detail")
    public ResponseUtils songListLikeTitle(@RequestParam("title") String title) {
        return ResponseUtils.success("查询成功", songListService.songListLikeTitle(title));
    }

    /**
     * 根据style模糊查询歌单
     * @param style
     * @return
     */
    @ApiOperation(value = "根据title精确查询歌单", notes = "根据title精确查询歌单")
    @PostMapping("/style/like/detail")
    public ResponseUtils songListLikeStyle(@RequestParam("style") String style) {
        return ResponseUtils.success("查询成功", songListService.songListLikeStyle(style));
    }

}
