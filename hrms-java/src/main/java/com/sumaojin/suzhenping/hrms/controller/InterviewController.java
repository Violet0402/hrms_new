package com.sumaojin.suzhenping.hrms.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.InterviewCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.InterviewDeleteDTO;
import com.sumaojin.suzhenping.hrms.dto.InterviewFind;
import com.sumaojin.suzhenping.hrms.dto.RequirementDTO;
import com.sumaojin.suzhenping.hrms.entity.Interview;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IInterviewService;
import com.sumaojin.suzhenping.hrms.service.IRequirementService;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.InterviewFindVM;
import com.sumaojin.suzhenping.hrms.vm.PostVM;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Resource
    private IInterviewService interviewService;
    @Resource
    private IRequirementService requirementService;
    @Resource
    private IDepartmentService departmentService;

    /**
     * 按需查询
     * @param dto
     * @param request
     * @return
     */
    @GetMapping(value = "/findList", produces = "application/json;charset=utf-8")
    public CommonResult<IPage<InterviewFindVM>> findList(InterviewFind dto, HttpServletRequest request){
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getCurrentPage() == null){
            dto.setCurrentPage(1);
        }
        return interviewService.findList(dto, request);
    }

    /**
     * 获取岗位
     * @return
     */
    @GetMapping(value = "/getJob", produces = "application/json;charset=utf-8")
    public CommonResult<List<PostVM>> getJob(){
        return new CommonResult<List<PostVM>>(requirementService.getPost());
    }

    /**
     * 创建面试
     * @param request
     * @param dto
     * @return
     */
    @PostMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult createInterview(HttpServletRequest request, InterviewCreateDTO dto){
        Boolean flag = interviewService.createInterview(dto, request);
        if (flag){
            return new CommonResult(null);
        }else{
            return new CommonResult(444, "创建失败");
        }
    }

    @PostMapping(value = "/deleteAll", produces = "application/json;charset=utf-8")
    public CommonResult deletePatch(@RequestBody String dtos){
        if (dtos != null){
            List<InterviewDeleteDTO> interviewDeleteDTOS = JSONArray.parseArray(dtos, InterviewDeleteDTO.class);
            ArrayList<Long> ids = new ArrayList<>();
            for (InterviewDeleteDTO d : interviewDeleteDTOS){
                ids.add(d.getId());
            }
            boolean isDelete = interviewService.remove(new UpdateWrapper<Interview>().in("id", ids));
            if (isDelete){
                return new CommonResult(null);
            }else {
                return new CommonResult(444, "操作失败");
            }
        }else{
            return new CommonResult(null);
        }
    }

    /**
     * 删除面试管理
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}", produces = "application/json;charset=utf-8")
    public CommonResult deleteById(@PathVariable("id") Long id){
        boolean isDelete = interviewService.remove(new UpdateWrapper<Interview>().eq("id", id));
        if (isDelete){
            return new CommonResult(null);
        }else{
            return new CommonResult(444, "删除失败");
        }
    }

    /**
     * 更新实体
     * @param dto
     * @return
     */
    @PutMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult edit(@RequestBody InterviewDeleteDTO dto){
        Interview interview = new Interview();
        BeanUtils.copyProperties(dto, interview);
        interview.setInterviewTime(Utils.stringToTimestap(dto.getInterviewTime()));
        Long departmentId = requirementService.getDepartmentIdByPost(dto.getJob());
        interview.setDepartmentId(departmentId);
        boolean isUpdate = interviewService.updateById(interview);
        if (isUpdate){
            return new CommonResult(null);
        }else {
            return new CommonResult(444, "操作失败");
        }

    }
}
