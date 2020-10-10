package com.sumaojin.suzhenping.hrms.controller;


import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.vm.DepartmentVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private IDepartmentService departmentService;

    /**
     * 获取部门
     * @return
     */
    @GetMapping(value = "/getDepartment" , produces = "application/json;charset=utf-8")
    public CommonResult<List<DepartmentVM>> getDepartment(){
        return departmentService.getDepartment();
    }
}
