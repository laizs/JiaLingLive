package com.gzzsc.lai.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ResultResponse
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/10 14:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private boolean status;
    private String msg;
    private List data;
    private Long total;
}
