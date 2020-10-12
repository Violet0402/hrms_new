package com.sumaojin.suzhenping.hrms.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonDeserializer;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author SMJ
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("departmentId")
    private Long departmentId;

    @TableField("joinTime")
    private Timestamp joinTime;

    @TableField("officialTime")
    private Timestamp officialTime;

    @TableField("endTime")
    private Timestamp endTime;

    @TableField("gender")
    private String gender;

    @TableField("roleId")
    private Integer roleId;

    @TableField("education")
    private String education;

    @TableField("major")
    private String major;

    @TableField("salary")
    private Integer salary;

    @TableField("isEnd")
    private String isEnd;

    @TableField("isOfficial")
    private String isOfficial;

    @TableField("post")
    private String post;

    @TableField("entryId")
    private Long entryId;

    @TableField("endId")
    private Long endId;
}
