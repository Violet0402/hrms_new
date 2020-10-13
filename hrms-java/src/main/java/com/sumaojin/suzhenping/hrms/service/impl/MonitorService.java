package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sumaojin.suzhenping.hrms.dto.MonitorDTO;
import com.sumaojin.suzhenping.hrms.entity.Entry;
import com.sumaojin.suzhenping.hrms.entity.Interview;
import com.sumaojin.suzhenping.hrms.entity.Menu;
import com.sumaojin.suzhenping.hrms.mapper.MenuMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEntryService;
import com.sumaojin.suzhenping.hrms.service.IInterviewService;
import com.sumaojin.suzhenping.hrms.service.IMonitorService;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.MonitorVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.monitor.MonitorMBean;
import java.sql.Timestamp;
import java.util.List;

@Service
public class MonitorService implements IMonitorService {
    @Resource
    private IEntryService entryService;
    @Resource
    private IInterviewService interviewService;
    /**
     * 获取数据
     * @param dto
     * @return
     */
    @Override
    public CommonResult<List<MonitorVM>> getData(MonitorDTO dto) {
        Timestamp startTime = Utils.stringToYearStart(dto.getYear());
        Timestamp endTime = Utils.stringToYearEnd(dto.getYear());
        List<Entry> entrys = entryService.list(new QueryWrapper<Entry>().select("joinTime", "name", "gender", "age", "major").groupBy("joinTime", "name", "gender", "age", "major").le("joinTime", endTime).ge("joinTime", startTime));
        interviewService.list(new QueryWrapper<Interview>().select(""));

        return null;
    }
}
