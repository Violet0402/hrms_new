package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EntryCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EntryDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEntryService;
import com.sumaojin.suzhenping.hrms.vm.EntryVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/entry")
public class EntryController {
    @Resource
    private IEntryService entryService;

    /**
     * 按需查询
     * @param dto
     * @return
     */
    @GetMapping(value = "/findList", produces = "application/json;charset=utf-8")
    public CommonResult<IPage<EntryVM>> findList(EntryDTO dto){
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getCurrentPage() == null){
            dto.setCurrentPage(1);
        }
        return entryService.findList(dto);
    }

    /**
     * 办理入职
     * @return
     */
    @PostMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult create(EntryCreateDTO dto){
        Boolean isSave = entryService.create(dto);
        if (isSave){
            return new CommonResult(null);
        }else {
            return new CommonResult(444, "操作失败");
        }
    }
}
