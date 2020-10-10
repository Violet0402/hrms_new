package com.sumaojin.suzhenping.hrms.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class EntryCreateDTO {
    private String name;
    private String gender;
    private Integer age;
    private String education;
    private String major;
    private String post;
    private Long department;
    private Integer intershipSalary;
    private Integer officialSalary;
    private String isOfficial;
}
