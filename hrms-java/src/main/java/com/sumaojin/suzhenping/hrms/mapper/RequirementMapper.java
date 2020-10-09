package com.sumaojin.suzhenping.hrms.mapper;

import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@Mapper
public interface RequirementMapper extends BaseMapper<Requirement> {

    @Select("select post from requirement")
    List<String> getPost();

    @Select("select departmentId from requirement where post = #{job}")
    Long getDepartmentIdByPost(String job);
}
