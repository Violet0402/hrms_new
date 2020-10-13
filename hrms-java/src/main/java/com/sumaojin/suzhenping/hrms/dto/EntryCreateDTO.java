package com.sumaojin.suzhenping.hrms.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class EntryCreateDTO {
    private Long id;
    private String joinTime;
    private String name;
    private String interviewTime;
    private Integer intershipSalary;
    private Integer officialSalary;
    private String isOfficial;
    private String officialTime;
}
