package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.sumaojin.suzhenping.hrms.dto.LoginDTO;
import com.sumaojin.suzhenping.hrms.entity.Identity;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IIdentityService;
import com.sumaojin.suzhenping.hrms.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-24
 */
@CrossOrigin
@Api(tags = "Account", description = "账户", value = "账户")
@RestController
@RequestMapping("/account")
public class IdentityController {

    @Resource
    private IIdentityService iIdentityService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    public CommonResult<Identity> login(LoginDTO dto, HttpServletResponse response){
        Identity account = iIdentityService.getOne(new QueryWrapper<Identity>().eq("username", dto.getUsername()));
        if (account == null || !account.getPassword().equals(dto.getPassword())){
            return new CommonResult<>(400, "账号或密码不正确");
        }
        String token = JwtUtils.generateToken(account.getUId());
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return new CommonResult(map);
    }
}
