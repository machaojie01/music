package com.mcj.music.controller;

import com.mcj.music.annotation.CheckParam;
import com.mcj.music.domain.Admin;
import com.mcj.music.service.AdminService;
import com.mcj.music.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author mcj
 * @date 2022/10/9 10:39
 * @email 2037612492@qq.com
 * @description 管理员controller
 */
@RestController
@Api(value = "管理员控制类", tags = "与管理员相关的接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 验证用户名和密码是否正确
     * @param admin
     * @return
     */
    @PostMapping("/admin/login/status")
    @ApiOperation(value = "验证用户名和密码是否正确", notes = "验证用户名和密码是否正确")
    @CheckParam
    public ResponseUtils loginStatus(@RequestBody Admin admin) {
        String name = admin.getName();
        String password = admin.getPassword();
        Map<String, String> result = adminService.verifyPassword(name, password);
        return ResponseUtils.success("登陆成功", result);
    }

    @PostMapping("/admin/logout")
    public ResponseUtils logout() {
        return adminService.logout();
    }

}
