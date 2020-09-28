package com.sumaojin.suzhenping.hrms.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Identity对象", description="")
public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("uid")
    private Integer uId;


}
