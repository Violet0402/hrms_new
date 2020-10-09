package com.sumaojin.suzhenping.hrms.service;

import com.sumaojin.suzhenping.hrms.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
