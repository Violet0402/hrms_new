package com.sumaojin.suzhenping.hrms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EntryCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EntryDTO;
import com.sumaojin.suzhenping.hrms.entity.Entry;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.EntryVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-10-10
 */
public interface IEntryService extends IService<Entry> {

    CommonResult<IPage<EntryVM>> findList(EntryDTO dto);

    Boolean create(EntryCreateDTO dto);
}
