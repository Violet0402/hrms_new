package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sumaojin.suzhenping.hrms.dto.EmployeeDTO;
import com.sumaojin.suzhenping.hrms.dto.JoinTimeDTO;
import com.sumaojin.suzhenping.hrms.entity.Department;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.entity.Interview;
import com.sumaojin.suzhenping.hrms.mapper.EmployeeMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.EmployeeNamesVM;
import com.sumaojin.suzhenping.hrms.vm.EmployeeVM;
import com.sumaojin.suzhenping.hrms.vm.InterviewTimeVM;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Resource
    private IDepartmentService departmentService;

    /**
     * 按需查询
     * @param dto
     * @param request
     * @return
     */
    @Override
    public CommonResult<IPage<EmployeeVM>> findList(EmployeeDTO dto, HttpServletRequest request) {
        Page<Employee> page = new Page<>(dto.getCurrentPage(), dto.getSize());
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("isEnd","joinTime");
        if (dto.getEndTime() != null && dto.getStartTime() != null){
            Timestamp startTime = Utils.stringToTimestap(dto.getStartTime());
            Timestamp endTime = Utils.stringToTimestap(dto.getEndTime());
            queryWrapper.le("joinTime", endTime).ge("joinTime", startTime);
        }
        if (dto.getName() != null){
            queryWrapper.like("name", dto.getName());
        }
        if (dto.getIsEnd() != null && dto.getIsEnd().length()>0){
            queryWrapper.eq("isEnd", dto.getIsEnd());
        }
        if (dto.getIsOfficial() != null && dto.getIsOfficial().length() > 0){
            queryWrapper.eq("isOfficial", dto.getIsOfficial());
        }
        Page<Employee> pageData = page(page, queryWrapper);
        Page<EmployeeVM> vmData = new Page<>();
        BeanUtils.copyProperties(pageData,vmData);
        List<Employee> records = pageData.getRecords();
        ArrayList<EmployeeVM> vms = new ArrayList<>();

        List<Department> departmentList = departmentService.list();


        for (Employee r : records){
            EmployeeVM vm = new EmployeeVM();
            BeanUtils.copyProperties(r,vm);
            for (Department d : departmentList){
                if (d.getId().equals(r.getDepartmentId())){
                    vm.setDepartment(d.getDepartment());
                }
            }
            if (r.getEndTime() != null){
                vm.setEndTime(Utils.timeToString(r.getEndTime()));
            }
            if (r.getJoinTime() != null){
                vm.setJoinTime(Utils.timeToString(r.getJoinTime()));
            }
            if (r.getOfficialTime() != null){
                vm.setOfficialTime(Utils.timeToString(r.getOfficialTime()));
            }
            vms.add(vm);
        }
        vmData.setRecords(vms);
        return new CommonResult<IPage<EmployeeVM>>(vmData);
    }

    /**
     * 获取名字
     * @return
     */
    @Override
    public CommonResult<List<EmployeeNamesVM>> getNames() {
        List<Employee> interviews = list(new QueryWrapper<Employee>().select("name").groupBy("name"));
        List<EmployeeNamesVM> vms = new ArrayList<>();
        for(Employee i : interviews){
            EmployeeNamesVM vm = new EmployeeNamesVM();
            vm.setLabel(i.getName());
            vm.setValue(i.getName());
            vms.add(vm);
        }
        return new CommonResult<>(vms);
    }

    /**
     * 获取入职时间
     * @param dto
     * @return
     */
    @Override
    public CommonResult<List<EmployeeNamesVM>> getJoinTimes(JoinTimeDTO dto) {
        if (dto.getName() == null || dto.getName().length() == 0){
            return new CommonResult<>(null);
        }
        List<Employee> interviews = list(new QueryWrapper<Employee>().select("joinTime").eq("name", dto.getName()));
        ArrayList<EmployeeNamesVM> vms = new ArrayList<>();
        for(Employee i : interviews){
            EmployeeNamesVM vm = new EmployeeNamesVM();
            vm.setLabel(Utils.timeToString(i.getJoinTime()));
            vm.setValue(Utils.timeToString(i.getJoinTime()));
            vms.add(vm);
        }
        return new CommonResult<>(vms);
    }
}
