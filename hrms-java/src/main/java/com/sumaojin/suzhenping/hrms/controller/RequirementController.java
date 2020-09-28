package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.RequirementCreate;
import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;
import com.sumaojin.suzhenping.hrms.dto.RequirementDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IRequirementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/requirement")
public class RequirementController {
    @Resource
    private IRequirementService requirementService;

    @GetMapping(value = "/findList", produces = "application/json;charset=utf-8")
    public CommonResult<IPage<RequirementVM>> findList(RequirementDTO dto, HttpServletRequest request){
        return requirementService.findList(dto, request);
    }

    @PostMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult createRequirement(RequirementCreate dto, HttpServletRequest request){
        Boolean flag = requirementService.create(dto, request);
        if (flag){
            return new CommonResult(null);
        }else{
            return new CommonResult(444,"操作失败");
        }

    }
}
