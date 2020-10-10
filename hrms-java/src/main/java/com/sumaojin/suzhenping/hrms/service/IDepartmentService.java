package com.sumaojin.suzhenping.hrms.service;

import com.sumaojin.suzhenping.hrms.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.DepartmentVM;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
public interface IDepartmentService extends IService<Department> {

    Long getDepartmentIdByName(String department);

    CommonResult<List<DepartmentVM>> getDepartment();
}
