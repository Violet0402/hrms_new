package com.sumaojin.suzhenping.hrms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.InterviewCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.InterviewFind;
import com.sumaojin.suzhenping.hrms.entity.Interview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.InterviewFindVM;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-10-09
 */
public interface IInterviewService extends IService<Interview> {

    CommonResult<IPage<InterviewFindVM>> findList(InterviewFind dto, HttpServletRequest request);

    Boolean createInterview(InterviewCreateDTO dto, HttpServletRequest request);
}
