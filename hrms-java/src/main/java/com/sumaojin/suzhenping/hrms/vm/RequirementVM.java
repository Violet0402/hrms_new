package com.sumaojin.suzhenping.hrms.vm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequirementVM {
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
}
