package com.sumaojin.suzhenping.hrms.vm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EntryVM {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    @TableId("id")
    private Long id;

    private String joinTime;

    private String name;

    private String gender;

    private Integer age;

    private String education;

    private String major;

    private String post;

    private String department;

    private Integer intershipSalary;

    private Integer officialSalary;
}
