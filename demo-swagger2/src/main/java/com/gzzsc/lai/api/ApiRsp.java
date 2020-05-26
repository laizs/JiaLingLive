package com.gzzsc.lai.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ApiRsp
 * @Deacription api响应
 * @Author laizs
 * @Date 2020/5/6 11:57
 **/
@Data
@ApiModel
public class ApiRsp<T> {
    @ApiModelProperty(value = "状态码",required = true,notes = "0 成功，非0是错误码")
    private int status=0;
    @ApiModelProperty(value = "错误信息")
    private String msg="";
    @ApiModelProperty(value = "业务数据内容")
    private T body;
}
