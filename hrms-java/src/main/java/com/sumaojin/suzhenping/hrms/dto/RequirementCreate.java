package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequirementCreate {
    private String post;
    private Integer nums;
    private String requirement;
    private String education;
    private String major;
    private String homeTime;
}
