package com.sumaojin.suzhenping.hrms.dto;

import lombok.Data;

@Data
public class EndEditDTO {
    private Long id;
    private String endTime;
    private String name;
    private String isAutoEnd;
    private String isDispute;
}
