package com.sumaojin.suzhenping.hrms.mapper;

import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("select r.resource from employee e left join role r on e.roleId = r.id where e.id = #{userId}")
    Integer getResource(Integer userId);

    @Select("select roleId from employee where id = #{userId}")
    Long getRoleId(Long userId);

    @Select("select departmentId from employee where id = #{userId}")
    Long getDepartmentIdByUID(Long userId);
}
