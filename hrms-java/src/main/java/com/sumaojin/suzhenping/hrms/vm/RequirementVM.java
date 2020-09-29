package com.sumaojin.suzhenping.hrms.vm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonDeserializer;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequirementVM {
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    @TableId("id")
    private Long id;

    private String department;

    private String proposer;

    private String post;

    private String requirement;

    private String education;

    private String major;

    private String hopeTime;

    private Integer nums;

    private String createTime;
}
