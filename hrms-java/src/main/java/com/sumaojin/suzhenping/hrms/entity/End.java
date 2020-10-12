package com.sumaojin.suzhenping.hrms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Timestamp;

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
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="End对象", description="")
public class End implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    private String name;

    private String post;

    @TableField("departmentId")
    private Long departmentId;

    @TableField("isAutoEnd")
    private String isAutoEnd;

    @TableField("isDispute")
    private String isDispute;

    @TableField("endTime")
    private Timestamp endTime;

    @TableField("compensation")
    private Integer compensation;
}
