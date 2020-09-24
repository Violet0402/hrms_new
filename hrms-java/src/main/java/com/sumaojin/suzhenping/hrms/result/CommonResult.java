package com.sumaojin.suzhenping.hrms.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(T data){
        this(200, "操作成功", data);
    }
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
