package com.sumaojin.suzhenping.hrms.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Entry对象", description="")
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("joinTime")
    private Timestamp joinTime;

    private String name;

    private String gender;

    private Integer age;

    private String education;

    private String major;

    private String post;

    @TableField("departmentId")
    private Long departmentId;

    @TableField("intershipSalary")
    private Integer intershipSalary;

    @TableField("officialSalary")
    private Integer officialSalary;

    @TableField("isOfficial")
    private String isOfficial;

}
