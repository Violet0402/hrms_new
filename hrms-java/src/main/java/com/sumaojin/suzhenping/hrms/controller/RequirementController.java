package com.sumaojin.suzhenping.hrms.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.RequirementCreate;
import com.sumaojin.suzhenping.hrms.dto.RequirementSelectDTO;
import com.sumaojin.suzhenping.hrms.dto.TempSelect;
import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;
import com.sumaojin.suzhenping.hrms.dto.RequirementDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IRequirementService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@RequestMapping("/requirement")
public class RequirementController {
    @Resource
    private IRequirementService requirementService;

    @GetMapping(value = "/findList", produces = "application/json;charset=utf-8")
    public CommonResult<IPage<RequirementVM>> findList(RequirementDTO dto, HttpServletRequest request){
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getCurrentPage() == null){
            dto.setCurrentPage(1);
        }
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

    @DeleteMapping(value = "/{id}", produces = "application/json;charset=utf-8")
    public CommonResult deleteRequirement(@PathVariable("id") Long id){
        boolean flag = requirementService.remove(new QueryWrapper<Requirement>().eq("id", id));
        if (flag){
            return new CommonResult(null);
        }else{
            return new CommonResult(444, "删除失败");
        }
    }

    @PostMapping(value = "/deleteAll", produces = "application/json;charset=utf-8")
    public CommonResult deleteSelect(@RequestBody String dtos){
        List<RequirementSelectDTO> requirementSelectDTOS = JSONArray.parseArray(dtos, RequirementSelectDTO.class);
        ArrayList<Long> lists = new ArrayList<>();
        for (RequirementSelectDTO d : requirementSelectDTOS){
            lists.add(d.getId());
        }
        boolean flag = requirementService.remove(new QueryWrapper<Requirement>().in("id", lists));
        if(flag){
            return new CommonResult(null);
        }else {
            return new CommonResult(444, "删除失败");
        }
    }

    @PutMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult alterRequirement(@RequestBody RequirementSelectDTO dto){
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(dto, requirement);
        requirement.setHopeTime(Utils.stringToTimestap(dto.getHopeTime()));
        boolean flag = requirementService.updateById(requirement);
        if (flag){
            return new CommonResult(null);
        }else{
            return new CommonResult(444, "编辑失败");
        }

    }
}
