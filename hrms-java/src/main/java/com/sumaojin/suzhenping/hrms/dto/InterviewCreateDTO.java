package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class InterviewCreateDTO {
    private String interviewTime;
    private String name;
    private String gender;
    private Integer age;
    private String education;
    private String major;
    private String job;
    private String stage;
    private String result;
    private String isStore;
    private String assess;
}
