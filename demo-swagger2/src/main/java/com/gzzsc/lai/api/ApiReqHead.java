package com.gzzsc.lai.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ApiReqHead
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/6 11:20
 **/
@Data
@ApiModel
public class ApiReqHead {
    @ApiModelProperty(value="时间戳",required = true,example ="1577811661000")
    private long timeStamp;
    @ApiModelProperty(value ="访问令牌",required = true,example = "asdfghjklqwertyuiozxcvb")
    private String token;
}
