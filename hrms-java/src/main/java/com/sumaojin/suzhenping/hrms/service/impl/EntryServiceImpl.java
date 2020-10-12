package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sumaojin.suzhenping.hrms.dto.EntryCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EntryDTO;
import com.sumaojin.suzhenping.hrms.dto.EntryEditDTO;
import com.sumaojin.suzhenping.hrms.entity.*;
import com.sumaojin.suzhenping.hrms.mapper.EntryMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.service.IEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.service.IInterviewService;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.EntryVM;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 * @since 2020-10-10
 */
@Service
public class EntryServiceImpl extends ServiceImpl<EntryMapper, Entry> implements IEntryService {
    @Resource
    private IDepartmentService departmentService;
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IInterviewService interviewService;
    /**
     * 按需查询
     * @param dto
     * @return
     */
    @Override
    public CommonResult<IPage<EntryVM>> findList(EntryDTO dto) {
        Page<Entry> page = new Page<>(dto.getCurrentPage(), dto.getSize());
        QueryWrapper<Entry> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("joinTime");

        if (dto.getEndTime() != null && dto.getStartTime() != null){
            Timestamp startTime = Utils.stringToTimestap(dto.getStartTime());
            Timestamp endTime = Utils.stringToTimestap(dto.getEndTime());
            queryWrapper.le("joinTime", endTime).ge("joinTime", startTime);
        }
        if (dto.getName() != null){
            queryWrapper.like("name", dto.getName());
        }
        Page<Entry> pageData = page(page, queryWrapper);
        Page<EntryVM> vmData = new Page<>();
        BeanUtils.copyProperties(pageData,vmData);
        List<Entry> records = pageData.getRecords();
        ArrayList<EntryVM> vms = new ArrayList<>();
        List<Department> departmentList = departmentService.list();

        for (Entry r : records){
            EntryVM vm = new EntryVM();
            BeanUtils.copyProperties(r,vm);
            vm.setJoinTime(Utils.timeToString(r.getJoinTime()));

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
     * 创建入职
     * @param dto
     * @return
     */
    @Override
    public Boolean create(EntryCreateDTO dto) {
        Entry entry = new Entry();
        Interview interview = interviewService.getOne(new QueryWrapper<Interview>().eq("name", dto.getName()).eq("interviewTime", Utils.stringToDate(dto.getInterviewTime())));
        BeanUtils.copyProperties(interview,entry);

        entry.setId(null);
        entry.setJoinTime(Utils.stringToTimestap(dto.getJoinTime()));
        entry.setPost(interview.getJob());
        entry.setIntershipSalary(dto.getIntershipSalary());
        entry.setOfficialSalary(dto.getIntershipSalary());
        entry.setInterviewTime(interview.getInterviewTime());
        entry.setIsOfficial(dto.getIsOfficial());
        Entry one = getOne(new QueryWrapper<Entry>().eq("name", entry.getName()).eq("interviewTime", interview.getInterviewTime()));
        if (one != null){
            return false;
        }
        boolean isSaveEntry = save(entry);

        Employee employee = new Employee();
        BeanUtils.copyProperties(entry, employee);
        employee.setSalary(entry.getOfficialSalary());
        employee.setIsEnd("否");
        employee.setEntryId(entry.getId());
        employee.setId(null);
        if (entry.getIsOfficial().equals("是")){
            employee.setOfficialTime(new Timestamp(new Date().getTime()));
        }
        boolean isSaveEmployee = employeeService.save(employee);
        if (isSaveEmployee && isSaveEntry){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 编辑入职申请
     * @param dto
     * @return
     */
    @Override
    public Boolean editEntry(EntryEditDTO dto) {
        Entry entry = new Entry();
        BeanUtils.copyProperties(dto, entry);
        entry.setJoinTime(Utils.stringToTimestap(dto.getJoinTime()));
        List<Department> departments = departmentService.list();
        for (Department d : departments) {
            if (d.getDepartment().equals(dto.getDepartment())) {
                entry.setDepartmentId(d.getId());
            }
        }
        boolean isUpdateEntry = update(entry, new UpdateWrapper<Entry>().eq("id",dto.getId()));

        Employee employee = new Employee();
        BeanUtils.copyProperties(entry, employee);
        employee.setSalary(entry.getOfficialSalary());
        if (entry.getIsOfficial().equals("是")){
            employee.setOfficialTime(new Timestamp(new Date().getTime()));
        }
        boolean isUpdateEmployee = employeeService.update(employee, new UpdateWrapper<Employee>().eq("entryId", dto.getId()));
        if (isUpdateEmployee && isUpdateEntry){
            return true;
        }else {
            return false;
        }
    }
}
