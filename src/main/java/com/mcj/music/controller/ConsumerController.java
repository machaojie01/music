package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Consumer;
import com.mcj.music.domain.ListSong;
import com.mcj.music.domain.Song;
import com.mcj.music.service.ConsumerService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author mcj
 * @date 2022/10/16 17:11
 * @email 2037612492@qq.com
 * @description 前端用户controller
 */
@RestController
@RequestMapping("/consumer")
@Api(value = "前端用户controller", tags = "前端用户controller")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 新增前端用户
     * @param consumer
     * @return
     */
    @ApiOperation(value = "新增前端用户", notes = "新增前端用户")
    @PostMapping("/add")
    public ResponseUtils addListSong(@RequestBody Consumer consumer) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(consumer.getUsername()) || StringUtils.isEmpty(consumer.getPassword())) {
            responseUtils.setCode(500);
            responseUtils.setMsg("用户名和密码不能为空");
            return responseUtils;
        }
        boolean flag = consumerService.insert(consumer);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }

    /**
     * 修改前端用户
     * @param consumer
     * @return
     */
    @ApiOperation(value = "修改前端用户", notes = "修改前端用户")
    @PostMapping("/update")
    public ResponseUtils updateConsumer(@RequestBody Consumer consumer) {
        ResponseUtils responseUtils = new ResponseUtils();
        if (StringUtils.isEmpty(consumer.getUsername()) || StringUtils.isEmpty(consumer.getPassword())) {
            responseUtils.setCode(500);
            responseUtils.setMsg("用户名和密码不能为空");
            return responseUtils;
        }
        boolean flag = consumerService.update(consumer);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("修改成功");
        } else {
            responseUtils = ResponseUtils.fail("修改失败");
        }
        return responseUtils;
    }

    /**
     * 删除前端用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除前端用户", notes = "删除前端用户")
    @PostMapping("/delete")
    public ResponseUtils deleteListSong(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        JSONObject jsonObject = new JSONObject();
        boolean flag = consumerService.delete(id);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("删除成功");
        } else {
            responseUtils = ResponseUtils.fail("删除失败");
        }
        return responseUtils;
    }

    /**
     * 根据前端用户id查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据歌单id查询", notes = "根据歌单id查询")
    @PostMapping("/select/id")
    public ResponseUtils selectByPrimaryKey(@RequestParam("id") Integer id) {
        return ResponseUtils.success("查询成功", consumerService.selectByPrimaryKey(id));
    }

    /**
     * 查询所有的前端用户
     * @return
     */
    @ApiOperation(value = "查询所有的前端用户", notes = "查询所有的前端用户")
    @PostMapping("/all")
    public ResponseUtils allConsumer() {
        return ResponseUtils.success("查询成功", consumerService.allConsumer());
    }

    /**
     * 更新前端用户头像
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新前端用户头像", notes = "更新前端用户头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "更新前端用户头像", required = true),
            @ApiImplicitParam(name = "id", value = "前端用户的id", required = true)
    })
    @PostMapping(value = "/update/pic")
    public ResponseUtils updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("文件上传失败");
            return responseUtils;
        }
        // 查询该歌曲所在的位置，根据文件路径删除本地歌曲
        Consumer oldConsumer = consumerService.selectByPrimaryKey(id);
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + oldConsumer.getAvator();
        File oldFile = new File(path);
        if (oldFile.exists()) {
            try {
                oldFile.delete();
            } catch (Exception e) {
                responseUtils.setCode(500);
                responseUtils.setMsg("旧头像删除失败");
                responseUtils.setData(e.getMessage());
                return responseUtils;
            }
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeAvatorPath = "avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if (flag) {
                responseUtils.setCode(200);
                responseUtils.setMsg("上传成功");
                responseUtils.setData("avatorImages:" + storeAvatorPath);
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

}
