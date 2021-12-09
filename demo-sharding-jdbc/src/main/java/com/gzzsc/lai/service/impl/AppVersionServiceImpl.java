package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.provider.entity.AppVersion;
import com.gzzsc.lai.mapper.AppVersionMapper;
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
        System.out.println("查询----");
        this.appVersionMapper.selectByPrimaryKey(appVersion.getId());
    }

    @Override
    public void saveWidthExceprion(AppVersion appVersion1, AppVersion appVersion2) {
        System.out.println("保存前先查询");
        this.appVersionMapper.selectByPrimaryKey(appVersion1.getId());
        this.appVersionMapper.insert(appVersion1);
        this.appVersionMapper.insert(appVersion2);
        //int i=1/0;
        System.out.println("保存后先查询");
        this.appVersionMapper.selectByPrimaryKey(appVersion1.getId());
    }
}
