package com.sumaojin.suzhenping.hrms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EndCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EndDTO;
import com.sumaojin.suzhenping.hrms.dto.EndEditDTO;
import com.sumaojin.suzhenping.hrms.entity.End;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.EndVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-10-12
 */
public interface IEndService extends IService<End> {

    CommonResult<IPage<EndVM>> findList(EndDTO dto);

    Boolean create(EndCreateDTO dto);

    Boolean edit(EndEditDTO dto);
}
