package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EmployeeDTO;
import com.sumaojin.suzhenping.hrms.dto.EmployeeEditDTO;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.vm.EmployeeVM;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;

    /**
     * 按需查询
     * @param dto
     * @param request
     * @return
     */
    @GetMapping(value = "/findList", produces = "application/json;charset=utf-8")
    public CommonResult<IPage<EmployeeVM>> findList(EmployeeDTO dto, HttpServletRequest request){
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getCurrentPage() == null){
            dto.setCurrentPage(1);
        }
        return employeeService.findList(dto, request);
    }

    /**
     * 编辑转正
     * @param dto
     * @return
     */
    @PutMapping(value = "", produces = "application/json;charset=utf-8")
    public CommonResult edit(@RequestBody EmployeeEditDTO dto){
        System.out.println(dto);
        if (dto.getIsOfficial().equals("是")){
            boolean isUpdate = employeeService.update(new UpdateWrapper<Employee>().eq("id", dto.getId()).set("isOfficial", dto.getIsOfficial()).set("officialTime", new Timestamp(new Date().getTime())));
            if (isUpdate){
                return new CommonResult(null);
            }else {
                return new CommonResult(444, "操作失败");
            }
        }else {
            return new CommonResult(null);
        }
    }
}
