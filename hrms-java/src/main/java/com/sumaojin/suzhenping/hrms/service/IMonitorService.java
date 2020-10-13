package com.sumaojin.suzhenping.hrms.service;

import com.sumaojin.suzhenping.hrms.dto.MonitorDTO;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.vm.MonitorVM;

import java.util.List;

public interface IMonitorService {
    CommonResult<List<MonitorVM>> getData(MonitorDTO dto);
}
