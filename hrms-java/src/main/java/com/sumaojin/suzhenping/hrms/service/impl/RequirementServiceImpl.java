package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sumaojin.suzhenping.hrms.dto.RequirementCreate;
import com.sumaojin.suzhenping.hrms.dto.RequirementDTO;
import com.sumaojin.suzhenping.hrms.entity.Department;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.sumaojin.suzhenping.hrms.mapper.DepartmentMapper;
import com.sumaojin.suzhenping.hrms.mapper.EmployeeMapper;
import com.sumaojin.suzhenping.hrms.mapper.IdentityMapper;
import com.sumaojin.suzhenping.hrms.mapper.RequirementMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.service.IIdentityService;
import com.sumaojin.suzhenping.hrms.service.IRequirementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.PostVM;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@Service
public class RequirementServiceImpl extends ServiceImpl<RequirementMapper, Requirement> implements IRequirementService {
    @Resource
    private IIdentityService identityService;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IDepartmentService departmentService;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private RequirementMapper requirementMapper;
    /**
     * 按需查询
     * @return
     * @param dto
     * @param request
     */
    @Override
    public CommonResult<IPage<RequirementVM>> findList(RequirementDTO dto, HttpServletRequest request) {
        Page<Requirement> page = new Page<>(dto.getCurrentPage(), dto.getSize());
        HttpSession session = request.getSession();
        Long userId = Long.parseLong((String) session.getAttribute("userId"));
        QueryWrapper<Requirement> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("createTime");
        Long roleId = employeeMapper.getRoleId(userId);
        if (!roleId.equals(1L)){
            queryWrapper.eq("proposer", userId);
        }
        if (dto.getEndTime() != null && dto.getStartTime() != null){
            Timestamp startTime = Utils.stringToTimestap(dto.getStartTime());
            Timestamp endTime = Utils.stringToTimestap(dto.getEndTime());
            queryWrapper.le("createTime", endTime).ge("createTime", startTime);
        }
        if (dto.getPost() != null){
            queryWrapper.like("post", dto.getPost());
        }
        Page<Requirement> pageData = page(page, queryWrapper);
        Page<RequirementVM> vmData = new Page<>();
        BeanUtils.copyProperties(pageData,vmData);
        List<Requirement> records = pageData.getRecords();
        ArrayList<RequirementVM> vms = new ArrayList<>();

        List<Employee> employeeList = employeeService.list(new QueryWrapper<Employee>().select("id", "name"));
        List<Department> departmentList = departmentService.list();

        for (Requirement r : records){
            RequirementVM vm = new RequirementVM();
            BeanUtils.copyProperties(r,vm);
            vm.setHopeTime(Utils.timeToString(r.getHopeTime()));
            vm.setCreateTime(Utils.timeToString(r.getCreateTime()));
            for (Department d : departmentList){
                if (d.getId().equals(r.getDepartmentId())){
                    vm.setDepartment(d.getDepartment());
                }
            }
            for (Employee e : employeeList){
                if (e.getId().equals(r.getProposer())){
                    vm.setProposer(e.getName());
                }
            }
            vms.add(vm);
        }
        vmData.setRecords(vms);
        return new CommonResult<>(vmData);
    }

    /**
     * 创建需求
     * @param dto
     * @param request
     * @return
     */
    @Override
    public Boolean create(RequirementCreate dto, HttpServletRequest request) {
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(dto, requirement);
        Long userId = Long.parseLong((String) request.getSession().getAttribute("userId"));
        requirement.setProposer(userId);
        Long departmentId = employeeMapper.getDepartmentIdByUID(userId);
        requirement.setDepartmentId(departmentId);
        requirement.setHopeTime(Utils.stringToTimestap(dto.getHopeTime()));
        requirement.setCreateTime(new Timestamp(new Date().getTime()));
        boolean flag = save(requirement);
        return flag;
    }

    /**
     * 获取岗位
     * @return
     */
    @Override

    public List<PostVM> getPost() {
        List<String> posts = requirementMapper.getPost();
        ArrayList<PostVM> vms = new ArrayList<>();
        for (String s : posts){
            PostVM vm = new PostVM();
            vm.setLabel(s);
            vm.setValue(s);
            vms.add(vm);
        }
        return vms;
    }

    /**
     * 根据岗位获取部门
     * @param post
     * @return
     */
    @Override
    public Long getDepartmentIdByPost(String post) {
        return requirementMapper.getDepartmentIdByPost(post);
    }
}
