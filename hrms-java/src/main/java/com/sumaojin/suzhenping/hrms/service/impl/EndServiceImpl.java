package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sumaojin.suzhenping.hrms.dto.EndCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EndDTO;
import com.sumaojin.suzhenping.hrms.dto.EndEditDTO;
import com.sumaojin.suzhenping.hrms.entity.Department;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.entity.End;
import com.sumaojin.suzhenping.hrms.entity.Entry;
import com.sumaojin.suzhenping.hrms.mapper.EndMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.service.IEndService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.EndVM;
import com.sumaojin.suzhenping.hrms.vm.EntryVM;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SMJ
 * @since 2020-10-12
 */
@Service
public class EndServiceImpl extends ServiceImpl<EndMapper, End> implements IEndService {
    @Resource
    private IDepartmentService departmentService;
    @Resource
    private IEmployeeService employeeService;

    /**
     * 按需查询
     * @param dto
     * @return
     */
    @Override
    public CommonResult<IPage<EndVM>> findList(EndDTO dto) {
        Page<End> page = new Page<>(dto.getCurrentPage(), dto.getSize());
        QueryWrapper<End> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("endTime");

        if (dto.getEndTime() != null && dto.getStartTime() != null){
            Timestamp startTime = Utils.stringToTimestap(dto.getStartTime());
            Timestamp endTime = Utils.stringToTimestap(dto.getEndTime());
            queryWrapper.le("endTime", endTime).ge("endTime", startTime);
        }
        if (dto.getName() != null){
            queryWrapper.like("name", dto.getName());
        }
        Page<End> pageData = page(page, queryWrapper);
        Page<EndVM> vmData = new Page<>();
        BeanUtils.copyProperties(pageData,vmData);
        List<End> records = pageData.getRecords();
        ArrayList<EndVM> vms = new ArrayList<>();
        List<Department> departmentList = departmentService.list();

        for (End r : records){
            EndVM vm = new EndVM();
            BeanUtils.copyProperties(r,vm);
            vm.setEndTime(Utils.timeToString(r.getEndTime()));

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
     * 创建离职
     * @param dto
     * @return
     */
    @Override
    public Boolean create(EndCreateDTO dto) {
        End end = new End();
        BeanUtils.copyProperties(dto,end);
        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("name", dto.getName()).eq("joinTime", dto.getJoinTime()));
        BeanUtils.copyProperties(employee, end);
        end.setEndTime(Utils.stringToTimestap(dto.getEndTime()));
        System.out.println(end);
        end.setId(null);
        End one = getOne(new QueryWrapper<End>().eq("name", dto.getName()).eq("endTime", Utils.stringToTimestap(dto.getEndTime())));
        if (one != null){
            return false;
        }
        boolean isSave = save(end);
        employee.setIsEnd("是");
        employee.setEndTime(end.getEndTime());
        employee.setEndId(end.getId());
        boolean isUpdata = employeeService.updateById(employee);
        if (isSave && isUpdata){
            return true;
        }
        return false;
    }

    /**
     * 编辑离职
     * @param dto
     * @return
     */
    @Override
    public Boolean edit(EndEditDTO dto) {
        boolean isEndUpdate = update(new UpdateWrapper<End>().eq("id", dto.getId()).set("name", dto.getName()).set("endTime", Utils.stringToTimestap(dto.getEndTime())).set("isAutoEnd", dto.getIsAutoEnd()).set("isDispute", dto.getIsDispute()));
        boolean isEmployeeUpdate = employeeService.update(new UpdateWrapper<Employee>().eq("name", dto.getName()).eq("endId", dto.getId()).set("endTime", Utils.stringToTimestap(dto.getEndTime())));
        if (isEmployeeUpdate && isEndUpdate){
            return true;
        }
        return false;
    }
}
