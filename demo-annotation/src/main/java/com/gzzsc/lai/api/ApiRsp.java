package com.gzzsc.lai.api;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ApiRsp
 * @Deacription api响应
 * @Author laizs
 * @Date 2020/5/6 11:57
 **/
@Data
@NoArgsConstructor
public class ApiRsp<T> {
    private int status=0;
    private String msg="";
    private T body;
}
