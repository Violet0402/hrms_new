package com.sumaojin.suzhenping.hrms.vm;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

@Data
public class EndVM {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    @TableId("id")
    private Long id;
    private String name;
    private String post;
    private String department;
    private String isAutoEnd;
    private String isDispute;
    private String endTime;
    private Integer compensation;
}
