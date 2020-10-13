package com.sumaojin.suzhenping.hrms.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

@Data
public class EntryEditDTO {
    private Long id;
    private String joinTime;
    private String name;
    private String gender;
    private Integer age;
    private String education;
    private String major;
    private String post;
    private String department;
    private Integer intershipSalary;
    private Integer officialSalary;
    private String isOfficial;
    private String officialTime;
}
