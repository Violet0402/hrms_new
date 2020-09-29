package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class RequirementSelectDTO {
    private Long id;
    private String department;
    private String proposer;
    private String post;
    private String requirement;
    private String education;
    private String major;
    private String hopeTime;
    private String createTime;
    private Integer nums;
}
