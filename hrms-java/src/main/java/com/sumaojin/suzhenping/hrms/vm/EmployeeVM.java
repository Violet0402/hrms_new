package com.sumaojin.suzhenping.hrms.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonDeserializer;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

@Data
public class EmployeeVM {
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    private String name;

    private String gender;

    private String education;

    private String major;

    private String department;

    private String post;

    private String joinTime;

    private String officialTime;

    private String endTime;

    private String isOfficial;

    private String isEnd;
}
