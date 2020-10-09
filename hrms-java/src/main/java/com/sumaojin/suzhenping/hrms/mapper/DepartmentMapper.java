package com.sumaojin.suzhenping.hrms.mapper;

import com.sumaojin.suzhenping.hrms.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("select id from department where department = #{department}")
    Long getDepartmentIdByName(String department);
}
