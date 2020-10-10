package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sumaojin.suzhenping.hrms.dto.InterviewCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.InterviewFind;
import com.sumaojin.suzhenping.hrms.entity.Department;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.entity.Interview;
import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.sumaojin.suzhenping.hrms.mapper.InterviewMapper;
import com.sumaojin.suzhenping.hrms.mapper.RequirementMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IInterviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.InterviewFindVM;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SMJ
 * @since 2020-10-09
 */
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview> implements IInterviewService {

    @Resource
    private IDepartmentService departmentService;
    @Resource
    private RequirementMapper requirementMapper;
    /**
     * 按需查询
     * @param dto
     * @param request
     * @return
     */
    @Override
    public CommonResult<IPage<InterviewFindVM>> findList(InterviewFind dto, HttpServletRequest request) {
        Page<Interview> page = new Page<>(dto.getCurrentPage(), dto.getSize());
        Long userId = Long.parseLong((String)(request.getSession().getAttribute("userId")));
        QueryWrapper<Interview> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("interviewTime");
        if (dto.getEndTime() != null && dto.getStartTime() != null){
            Timestamp startTime = Utils.stringToTimestap(dto.getStartTime());
            Timestamp endTime = Utils.stringToTimestap(dto.getEndTime());
            queryWrapper.le("interviewTime", endTime).ge("interviewTime", startTime);
        }
        if (dto.getName() != null){
            queryWrapper.like("name", dto.getName());
        }
        Page<Interview> pageData = page(page, queryWrapper);
        Page<InterviewFindVM> vmData = new Page<>();
        BeanUtils.copyProperties(pageData, vmData);

        List<Interview> records = pageData.getRecords();
        ArrayList<InterviewFindVM> vms = new ArrayList<>();

        List<Department> departmentList = departmentService.list();

        for (Interview r : records){
            InterviewFindVM vm = new InterviewFindVM();
            BeanUtils.copyProperties(r,vm);
            vm.setInterviewTime(Utils.timeToString(r.getInterviewTime()));

            for (Department d : departmentList){
                if (d.getId().equals(r.getDepartmentId())){
                    vm.setDepartment(d.getDepartment());
                }
            }
            vms.add(vm);
        }
        vmData.setRecords(vms);
        return new CommonResult<>(vmData);
    }

    /**
     * 创建面试管理
     * @param dto
     * @param request
     * @return
     */
    @Override
    public Boolean createInterview(InterviewCreateDTO dto, HttpServletRequest request) {
        Interview interview = new Interview();
        BeanUtils.copyProperties(dto, interview);
        interview.setInterviewTime(Utils.stringToTimestap(dto.getInterviewTime()));
        Long departmentId = requirementMapper.getDepartmentIdByPost(dto.getJob());

        interview.setDepartmentId(departmentId);
        boolean isSave = save(interview);
        return isSave;
    }
}
