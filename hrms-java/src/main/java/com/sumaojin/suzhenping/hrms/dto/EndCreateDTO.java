package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class EndCreateDTO {
    private String endTime;
    private String name;
    private String joinTime;
    private String isAutoEnd;
    private String isDispute;
    private Integer compensation;
}
