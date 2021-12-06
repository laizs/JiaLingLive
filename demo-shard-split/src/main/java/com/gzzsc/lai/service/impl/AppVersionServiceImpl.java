package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.mapper.AppVersionMapper;
import com.gzzsc.lai.provider.entity.AppVersion;
import com.gzzsc.lai.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AppVersionServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 11:12
 **/
@SuppressWarnings("ALL")
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;
    @Override
    public List<AppVersion> findAll() {
        return this.appVersionMapper.selectByExample(null);
    }

    @Override
    public void save(AppVersion appVersion) {
        this.appVersionMapper.insertSelective(appVersion);
    }
}
