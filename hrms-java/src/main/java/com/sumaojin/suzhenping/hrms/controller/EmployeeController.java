package com.sumaojin.suzhenping.hrms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sumaojin.suzhenping.hrms.dto.EmployeeDTO;
import com.sumaojin.suzhenping.hrms.dto.EmployeeEditDTO;
import com.sumaojin.suzhenping.hrms.dto.JoinTimeDTO;
import com.sumaojin.suzhenping.hrms.entity.Employee;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.EmployeeNamesVM;
import com.sumaojin.suzhenping.hrms.vm.EmployeeVM;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
            boolean isUpdate = employeeService.update(new UpdateWrapper<Employee>().eq("id", dto.getId()).set("isOfficial", dto.getIsOfficial()).set("officialTime", Utils.stringToTimestap(dto.getOfficialTime())));
            if (isUpdate){
                return new CommonResult(null);
            }else {
                return new CommonResult(444, "操作失败");
            }
        }else {
            return new CommonResult(null);
        }
    }

    /**
     * 获取名字
     * @return
     */
    @GetMapping("/getNames")
    public CommonResult<List<EmployeeNamesVM>> getNames(){
        return employeeService.getNames();
    }

    /**
     * 获取入职时间
     * @return
     */
    @GetMapping("/getJoinTimes")
    public CommonResult<List<EmployeeNamesVM>> getJoinTimes(JoinTimeDTO dto){
        return employeeService.getJoinTimes(dto);
    }

    /**
     * 获取用户名
     * @return
     */
    @GetMapping("/name")
    public CommonResult<String> getName(HttpServletRequest request){
        long userId = Long.parseLong((String) request.getSession().getAttribute("userId"));
        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("id", userId));
        return new CommonResult<>(employee.getName());
    }
}
