package com.gzzsc.lai.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ApiReq
 * @Deacription api请求封装体
 * @Author laizs
 * @Date 2020/5/6 11:18
 **/
@Data
@ApiModel
public class ApiReq<T> {
    @ApiModelProperty(value = "消息头")
    private ApiReqHead head;
    @ApiModelProperty(value = "业务数据")
    private T body;


}
