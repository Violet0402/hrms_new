package com.sumaojin.suzhenping.hrms.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Interview对象", description="")
public class Interview implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonDeserialize
    @JsonSerialize
    @TableId("id")
    private Long id;

    @TableField("interviewTime")
    private Timestamp interviewTime;

    private String name;

    private String gender;

    private Integer age;

    private String job;

    @TableField("departmentId")
    private Long departmentId;

    private String stage;

    private String result;

    @TableField("isStore")
    private String isStore;

    private String assess;

    private String education;

    private String major;
}
