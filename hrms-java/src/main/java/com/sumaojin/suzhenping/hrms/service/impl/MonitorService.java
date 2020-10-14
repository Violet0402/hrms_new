package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sumaojin.suzhenping.hrms.dto.MonitorDTO;
import com.sumaojin.suzhenping.hrms.entity.*;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.*;
import com.sumaojin.suzhenping.hrms.util.Utils;
import com.sumaojin.suzhenping.hrms.vm.MonitorVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MonitorService implements IMonitorService {
    @Resource
    private IEntryService entryService;
    @Resource
    private IInterviewService interviewService;
    @Resource
    private IEndService endService;
    @Resource
    private IEmployeeService employeeService;
    /**
     * 获取数据
     * @param dto
     * @return
     */
    @Override
    public CommonResult<List<MonitorVM>> getData(MonitorDTO dto) {
        ArrayList<MonitorVM> vms = new ArrayList<>();
        Timestamp startTime = Utils.stringToYearStart(dto.getYear());
        Timestamp endTime = Utils.stringToYearEnd(dto.getYear());
        System.out.println(dto.getYear());
        System.out.println(startTime);
        System.out.println(endTime);

        //入职率
        List<Entry> entrys = entryService.list(new QueryWrapper<Entry>().select("joinTime", "name", "gender", "age", "major").groupBy("joinTime", "name", "gender", "age", "major").le("joinTime", endTime).ge("joinTime", startTime));
        List<Interview> interviews = interviewService.list(new QueryWrapper<Interview>().select("interviewTime","name","gender","age","job").groupBy("interviewTime","name","gender","age","job").le("interviewTime",endTime).ge("interviewTime",startTime));

        List<Double> rRate = new ArrayList<>();
        if (entrys == null || entrys.size() == 0){
            //没查到入职情况
            for(int i = 0; i < 12; i++){
                rRate.add(0.0);
            }
        }else {
            //查到入职情况
            rRate = getrRate(entrys, interviews, dto.getYear());
        }
        MonitorVM rRateVM = new MonitorVM();
        rRateVM.setData(rRate);
        vms.add(rRateVM);


        //离职率
        List<End> ends = endService.list(new QueryWrapper<End>().select("name", "post", "departmentId", "endTime").groupBy("name", "post", "departmentId", "endTime").ge("endTime",startTime).le("endTime",endTime));
        List<Employee> employees = employeeService.list(new QueryWrapper<Employee>().select("name", "departmentId", "joinTime", "gender", "post").groupBy("name", "departmentId", "joinTime", "gender", "post"));
        System.out.println(employees);
        List<Double> lRate = new ArrayList<>();
        if (ends == null || ends.size() == 0){
            for(int i = 0; i < 12; i++){
                lRate.add(0.0);
            }
        }else {
            lRate = getlRate(ends, employees, dto.getYear());
        }

        MonitorVM lRateVM = new MonitorVM();
        lRateVM.setData(lRate);
        vms.add(lRateVM);


        return new CommonResult<>(vms);
    }

    /**
     * 获取离职率
     * @param ends
     * @param employees
     * @param year
     * @return
     */
    private List<Double> getlRate(List<End> ends, List<Employee> employees, String year) {
        ArrayList<Double> lRetes = new ArrayList<>();
        HashMap<String, Integer> endMap = getMap();
        List<Timestamp> endTimes = new ArrayList<>();
        for (End e : ends){
            endTimes.add(e.getEndTime());
        }
        int employeeSize = employees.size();
        endMap = getMapNums(endTimes, endMap, year);
        for (int i = 1; i <=12; i++){
            if (endMap.get(i+"") == 0){
                lRetes.add(0.0);
                continue;
            }
            Double v = (endMap.get(i + "") / 1.0) / employeeSize;
            BigDecimal bigDecimal = new BigDecimal(v);
            BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.HALF_UP);
            double v1 = bigDecimal1.doubleValue();
            lRetes.add(v1);
        }
        return lRetes;
    }

    /**
     * 获取入职率
     * @param entrys
     * @param interviews
     * @param year
     * @return
     */
    private List<Double> getrRate(List<Entry> entrys, List<Interview> interviews, String year) {
        ArrayList<Double> rRates = new ArrayList<>();
        HashMap<String, Integer> entryMap = getMap();
        HashMap<String, Integer> interviewMap = getMap();
        List<Timestamp> entryTimes = new ArrayList<>();
        List<Timestamp> interviewTimes = new ArrayList<>();
        for (Interview i : interviews){
            interviewTimes.add(i.getInterviewTime());
        }
        for (Entry e : entrys){
            entryTimes.add(e.getJoinTime());
        }
        entryMap = getMapNums(entryTimes, entryMap, year);
        interviewMap = getMapNums(interviewTimes, interviewMap, year);
        for (int i = 1; i <= 12; i++){
            if (entryMap.get(i+"") == 0 || interviewMap.get(i+"") == 0){
                rRates.add(0.0);
                continue;
            }
            Double v = (entryMap.get(i + "") / 1.0) / interviewMap.get(i + "");
            BigDecimal bigDecimal = new BigDecimal(v);
            BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.HALF_UP);
            double v1 = bigDecimal1.doubleValue();
            rRates.add(v1);
        }
        return rRates;
    }

    /**
     * 获取事件内的数量
     * @param times
     * @param map
     * @param year
     * @return
     */
    private HashMap<String, Integer> getMapNums(List<Timestamp> times, HashMap<String, Integer> map, String year) {
        for (Timestamp t : times){
            if ((t.after(Timestamp.valueOf(year+"-01-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-01-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-01-01 00:00:00"))){
                map.put("1",map.get("1")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-02-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-02-28 23:59:59")))||t.equals(Timestamp.valueOf(year+"-02-01 00:00:00"))){
                map.put("2",map.get("2")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-03-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-03-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-03-01 00:00:00"))){
                map.put("3",map.get("3")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-04-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-04-30 23:59:59")))||t.equals(Timestamp.valueOf(year+"-04-01 00:00:00"))){
                map.put("4",map.get("4")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-05-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-05-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-05-01 00:00:00"))){
                map.put("5",map.get("5")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-06-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-06-30 23:59:59")))||t.equals(Timestamp.valueOf(year+"-06-01 00:00:00"))){
                map.put("6",map.get("6")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-07-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-07-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-07-01 00:00:00"))){
                map.put("7",map.get("7")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-08-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-08-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-08-01 00:00:00"))){
                map.put("8",map.get("8")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-09-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-09-30 23:59:59")))||t.equals(Timestamp.valueOf(year+"-09-01 00:00:00"))){
                map.put("9",map.get("9")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-10-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-10-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-10-01 00:00:00"))){
                map.put("10",map.get("10")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-11-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-11-30 23:59:59")))||t.equals(Timestamp.valueOf(year+"-11-01 00:00:00"))){
                map.put("11",map.get("11")+1);
            }
            if ((t.after(Timestamp.valueOf(year+"-12-01 00:00:00")) && t.before(Timestamp.valueOf(year+"-12-31 23:59:59")))||t.equals(Timestamp.valueOf(year+"-12-01 00:00:00"))){
                map.put("12",map.get("12")+1);
            }
        }
        return map;
    }


    public HashMap<String, Integer> getMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1",0);
        map.put("2",0);
        map.put("3",0);
        map.put("4",0);
        map.put("5",0);
        map.put("6",0);
        map.put("7",0);
        map.put("8",0);
        map.put("9",0);
        map.put("10",0);
        map.put("11",0);
        map.put("12",0);
        return map;
    }
}
