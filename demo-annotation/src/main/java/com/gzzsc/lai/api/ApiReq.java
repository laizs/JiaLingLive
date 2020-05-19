package com.gzzsc.lai.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ApiReq
 * @Deacription api请求封装体
 * @Author laizs
 * @Date 2020/5/6 11:18
 **/
@Data
public class ApiReq<T> {
    private ApiReqHead head;
    private T body;


}
