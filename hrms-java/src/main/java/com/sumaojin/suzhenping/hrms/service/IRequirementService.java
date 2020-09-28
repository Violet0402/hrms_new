package com.sumaojin.suzhenping.hrms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.RequirementCreate;
import com.sumaojin.suzhenping.hrms.dto.RequirementDTO;
import com.sumaojin.suzhenping.hrms.entity.Requirement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.RequirementVM;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
public interface IRequirementService extends IService<Requirement> {

    CommonResult<IPage<RequirementVM>> findList(RequirementDTO dto, HttpServletRequest request);

    Boolean create(RequirementCreate dto, HttpServletRequest request);
}
