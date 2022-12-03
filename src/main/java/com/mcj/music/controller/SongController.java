package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Singer;
import com.mcj.music.domain.Song;
import com.mcj.music.service.SongService;
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
 * @date 2022/10/15 10:33
 * @email 2037612492@qq.com
 * @description 歌曲controller
 */
@Api(value = "歌曲控制类", tags = "和歌曲有关的一些操作接口")
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    @ApiOperation(value = "添加歌曲", notes = "添加歌曲")
    @PostMapping("/add")
    public ResponseUtils addSong(@RequestBody Song song, @RequestParam("file")MultipartFile mpFile) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        String pic = "img/SongPic/歌曲.jpg";
        // 上传歌曲文件
        if (mpFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("歌曲上传失败");
            return responseUtils;
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeUrlPath = "song/" + fileName;
        try {
            mpFile.transferTo(dest);
            song.setPic(pic);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
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
     * 根据歌手id查询歌曲
     * @param singerId
     * @return
     */
    @ApiOperation(value = "根据歌手id查询歌曲", notes = "根据歌手id查询歌曲")
    @PostMapping("/singer/detail")
    public ResponseUtils songOfSingerId(@RequestParam("singerId") Integer singerId) {
        return  ResponseUtils.success("查询成功", songService.songOfSingerId(singerId));
    }

    /**
     * 修改歌曲
     * @param song
     * @return
     */
    @ApiOperation(value = "修改歌曲", notes = "修改歌曲")
    @PostMapping("/update")
    public ResponseUtils updateSong(@RequestBody Song song) {
        ResponseUtils responseUtils;
        boolean flag = songService.update(song);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("修改成功");
        } else {
            responseUtils = ResponseUtils.fail("修改失败");
        }
        return responseUtils;
    }
    /**
     * 删除歌曲
     * @param id
     * @return
     */
    @ApiOperation(value = "删除歌曲", notes = "删除歌曲")
    @PostMapping("/delete")
    public ResponseUtils deleteSong(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        // 查询该歌曲所在的位置，根据文件路径删除本地歌曲
        Song song = songService.selectByPrimaryKey(id);
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + song.getUrl();
        File file = new File(filePath);
        try {
            file.delete();
        } catch (Exception e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("删除失败");
            responseUtils.setData(e.getMessage());
            return responseUtils;
        }
        boolean flag = songService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }

    /**
     * 更新歌曲图片
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新歌曲图片", notes = "更新歌曲图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "歌曲的图片", required = true),
            @ApiImplicitParam(name = "id", value = "歌曲的id", required = true)
    })
    @PostMapping(value = "/update/song/pic")
    public ResponseUtils updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("文件上传失败");
            return responseUtils;
        }
        // 查询该歌曲所在的位置，根据文件路径删除本地歌曲
        Song oldSong = songService.selectByPrimaryKey(id);
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + oldSong.getPic();
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
                + System.getProperty("file.separator") + "songPic";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeAvatorPath = "img/songPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.update(song);
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
     * 更新歌曲
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新歌曲", notes = "更新歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "歌曲", required = true),
            @ApiImplicitParam(name = "id", value = "歌曲的id", required = true)
    })
    @PostMapping(value = "/update/song")
    public ResponseUtils updateSong(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("文件上传失败");
            return responseUtils;
        }
        String[] split = avatorFile.getOriginalFilename().split("\\.");
        if (!"mp3".equals(split[split.length - 1])) {
            responseUtils.setCode(500);
            responseUtils.setMsg("上传的歌曲不是mp3格式");
            return responseUtils;
        }
        // 查询该歌曲所在的位置，根据文件路径删除本地歌曲
        Song oldSong = songService.selectByPrimaryKey(id);
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + oldSong.getUrl();
        File oldFile = new File(path);
        try {
            oldFile.delete();
        } catch (Exception e) {
            responseUtils.setCode(500);
            responseUtils.setMsg("旧歌曲删除失败");
            responseUtils.setData(e.getMessage());
            return responseUtils;
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeAvatorPath = "song/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
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
     * 根据歌手名字精确查询
     * @param name
     * @return
     */
    @ApiOperation(value = "根据歌手名字精确查询", notes = "根据歌手名字精确查询")
    @PostMapping("/song/name")
    public ResponseUtils songOfName(@RequestParam("name") String name) {
        return ResponseUtils.success("查询成功", songService.songOfName(name));
    }

    /**
     * 查询所有歌曲
     * @return
     */
    @ApiOperation(value = "查询所有歌曲", tags = "查询所有歌曲")
    @PostMapping("/all")
    public ResponseUtils allSong() {
        return ResponseUtils.success("查询成功", songService.allSong());
    }

}
