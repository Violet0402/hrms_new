package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class InterviewDeleteDTO {
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
