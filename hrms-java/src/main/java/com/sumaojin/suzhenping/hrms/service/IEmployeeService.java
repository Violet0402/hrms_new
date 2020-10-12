package com.sumaojin.suzhenping.hrms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EmployeeDTO;
import com.sumaojin.suzhenping.hrms.dto.JoinTimeDTO;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.EmployeeNamesVM;
import com.sumaojin.suzhenping.hrms.vm.EmployeeVM;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
public interface IEmployeeService extends IService<Employee> {

    CommonResult<IPage<EmployeeVM>> findList(EmployeeDTO dto, HttpServletRequest request);

    CommonResult<List<EmployeeNamesVM>> getNames();

    CommonResult<List<EmployeeNamesVM>> getJoinTimes(JoinTimeDTO dto);
}
