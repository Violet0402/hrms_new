package com.sumaojin.suzhenping.hrms.vm;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonDeserializer;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

@Data
public class InterviewFindVM {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @TableId("id")
    private Long id;

    private String interviewTime;

    private String name;

    private String gender;

    private Integer age;

    private String education;

    private String major;

    private String job;

    private String department;

    private String stage;

    private String result;

    private String isStore;

    private String assess;
}
