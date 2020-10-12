package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EndCreateDTO;
import com.sumaojin.suzhenping.hrms.dto.EndDTO;
import com.sumaojin.suzhenping.hrms.dto.EndEditDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEndService;
import com.sumaojin.suzhenping.hrms.vm.EndVM;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/end")
public class EndController {
    @Resource
    private IEndService endService;


    /**
     * 按需查询
     * @param dto
     * @return
     */
    @GetMapping("/findList")
    public CommonResult<IPage<EndVM>> findList(EndDTO dto){
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getCurrentPage() == null){
            dto.setCurrentPage(1);
        }
        return endService.findList(dto);
    }

    /**
     * 创建离职
     * @param dto
     * @return
     */
    @PostMapping("")
    public CommonResult create(EndCreateDTO dto){
        Boolean isCreate = endService.create(dto);
        if (isCreate){
            return new CommonResult(null);
        }else {
            return new CommonResult(444, "操作失败");
        }
    }

    /**
     * 编辑离职
     * @param dto
     * @return
     */
    @PutMapping("")
    public CommonResult edit(@RequestBody EndEditDTO dto){
        Boolean isEdit = endService.edit(dto);
        if (isEdit){
            return new CommonResult(null);
        }
        return new CommonResult(444, "操作失败");
    }
}
