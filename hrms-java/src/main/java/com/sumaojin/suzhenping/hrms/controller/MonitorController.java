package com.sumaojin.suzhenping.hrms.controller;

import com.sumaojin.suzhenping.hrms.dto.MonitorDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IMonitorService;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.MonitorVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Resource
    private IMonitorService monitorService;

    /**
     * 获取数据
     * @param dto
     * @return
     */
    @GetMapping("/data")
    public CommonResult<List<MonitorVM>> getData(MonitorDTO dto) throws ParseException {
        if (dto.getYear() == null || dto.getYear().length() == 0){
            dto.setYear(Utils.timeToYear(new Timestamp(new Date().getTime())));
        }
        return monitorService.getData(dto);
    }

    /**
     * 获取时间
     * @return
     */
    @GetMapping("/time")
    public CommonResult<String> getTime(){
        return new CommonResult<>(Utils.timeToYear(new Timestamp(new Date().getTime())));
    }
}
