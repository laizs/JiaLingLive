package com.gzzsc.lai.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ApiReqHead
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/6 11:20
 **/
@Data
public class ApiReqHead {
    private long timeStamp;
    private String token;
}
