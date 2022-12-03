package com.mcj.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcj.music.domain.Singer;
import com.mcj.music.service.SingerService;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @description 歌手控制类
 */
@Api(value = "歌手控制类", tags = "和歌手有关的一些接口")
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     * @param singer
     * @return
     */
    @ApiOperation(value = "添加歌手", notes = "添加歌手")
    @PostMapping("/add")
    public ResponseUtils addSinger(@RequestBody Singer singer) {
        ResponseUtils responseUtils;
        boolean flag = singerService.insert(singer);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("添加成功");
        } else {
            responseUtils = ResponseUtils.fail("添加失败");
        }
        return responseUtils;
    }

    /**
     * 修改歌手
     * @param singer
     * @return
     */
    @ApiOperation(value = "修改歌手", notes = "修改歌手")
    @PostMapping("/update")
    public ResponseUtils updateSinger(@RequestBody Singer singer) {
        ResponseUtils responseUtils;
        boolean flag = singerService.update(singer);
        if (flag) {  // 成功
            responseUtils = ResponseUtils.success("修改成功");
        } else {
            responseUtils = ResponseUtils.fail("修改失败");
        }
        return responseUtils;
    }

    /**
     * 删除歌手
     * @param id
     * @return
     */
    @ApiOperation(value = "删除歌手", notes = "删除歌手")
    @PostMapping("/delete")
    public ResponseUtils deleteSinger(@RequestParam("id") Integer id) {
        ResponseUtils responseUtils;
        boolean flag = singerService.delete(id);
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
    @ApiOperation(value = "根据id查询歌手", notes = "根据id查询歌手")
    @PostMapping("/select/id")
    public ResponseUtils selectByPrimaryKey(@RequestParam("id") Integer id) {
        return ResponseUtils.success("查询成功", singerService.selectByPrimaryKey(id));
    }

    /**
     * 查询所有歌手
     * @return
     */
    @ApiOperation(value = "查询所有歌手", notes = "查询所有歌手")
    @PostMapping("/all")
    //@PreAuthorize("hasAuthority('system:test:list')")
    // 调用自定义的权限认证方法 @ex其中ex是bean的名称，这是SPEL表达式可以通过这种方式获取容器中bean的名字为ex的bean
    @PreAuthorize("@ex.hasAuthority('system:test:list')")
    public ResponseUtils allSinger() {
        return ResponseUtils.success("查询成功", singerService.allSinger());
    }

    /**
     * 根据歌手名字模糊查询列表
     * @param name
     * @return
     */
    @ApiOperation(value = "根据名称模糊查询歌手", notes = "根据歌手名称模糊查询歌手")
    @PostMapping("/select/name")
    public ResponseUtils singerOfName(@RequestParam("name") String name) {
        return ResponseUtils.success("查询成功", singerService.singerOfName(name.trim()));
    }

    /**
     * 根据性别查询列表
     * @param sex
     * @return
     */
    @ApiOperation(value = "根据性别查询歌手", notes = "根据性别查询歌手")
    @PostMapping("/select/sex")
    public ResponseUtils singerOfSex(@RequestParam("sex") Integer sex) {
        return ResponseUtils.success("查询成功", singerService.singerOfSex(sex));
    }

    /**
     * 更新歌手图片
     * @param avatorFile
     * @param id
     * @return
     */
    @ApiOperation(value = "更新歌手图片", notes = "更新歌手图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "歌手的图片", required = true),
            @ApiImplicitParam(name = "id", value = "歌手的id", required = true)
    })
    @PostMapping(value = "/update/singer/pic")
    public ResponseUtils updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        ResponseUtils responseUtils = new ResponseUtils();
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            responseUtils.setCode(500);
            responseUtils.setMsg("文件上传失败");
            return responseUtils;
        }
        // 文件名 = 当前毫秒+原来文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        // 如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的实际文件
        String storeAvatorPath = "img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
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

}
