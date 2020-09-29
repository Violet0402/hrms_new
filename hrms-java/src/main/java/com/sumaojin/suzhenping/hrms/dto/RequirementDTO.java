package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class RequirementDTO {
    private String name;
    private Integer size = 5;
    private Integer currentPage = 1;
    private String startTime;
    private String endTime;
    private String post;
}
