package com.sumaojin.suzhenping.hrms.entity;

import java.io.Serializable;
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
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Test对象", description="")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;


}
