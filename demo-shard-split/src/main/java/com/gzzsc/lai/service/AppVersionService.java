package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.AppVersion;

import java.util.List;

/**
 * @ClassName AppVersionService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 11:11
 **/
public interface AppVersionService {
    List<AppVersion> findAll();

    void save(AppVersion appVersion);
}
