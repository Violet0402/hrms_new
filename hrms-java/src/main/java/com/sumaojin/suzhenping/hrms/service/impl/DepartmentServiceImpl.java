package com.sumaojin.suzhenping.hrms.service.impl;

import com.sumaojin.suzhenping.hrms.entity.Department;
import com.sumaojin.suzhenping.hrms.mapper.DepartmentMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.vm.DepartmentVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Long getDepartmentIdByName(String department) {
        return departmentMapper.getDepartmentIdByName(department);
    }

    /**
     * 获取部门
     * @return
     */
    @Override
    public CommonResult<List<DepartmentVM>> getDepartment() {
        List<Department>departments = list();
        ArrayList<DepartmentVM> vms = new ArrayList<>();
        for (Department d : departments){
            DepartmentVM vm = new DepartmentVM();
            vm.setLabel(d.getDepartment());
            vm.setValue(d.getDepartment());
            vms.add(vm);
        }
        return new CommonResult<>(vms);
    }
}
