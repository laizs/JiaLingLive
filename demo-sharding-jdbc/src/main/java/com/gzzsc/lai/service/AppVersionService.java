package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.AppVersion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AppVersionService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 11:11
 **/
public interface AppVersionService {
    List<AppVersion> findAll();

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    void save(AppVersion appVersion);

    /**
     * 测试保存抛运行时异常
     * @param appVersion
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    void saveWidthExceprion(AppVersion appVersion1,AppVersion appVersion2);
}
