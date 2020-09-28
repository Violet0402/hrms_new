package com.sumaojin.suzhenping.hrms.controller;


import com.sumaojin.suzhenping.hrms.entity.Menu;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-25
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;
    /*@Resource
    private */

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单")
    @GetMapping(value = "/findAll", produces = "application/json;charset=utf-8")
    public CommonResult<List<Menu>> findAll(HttpServletRequest request){
        return menuService.findAll(request);
    }
}
