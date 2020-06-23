package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.Cfg;
import com.gzzsc.lai.mapper.CfgMapper;
import com.gzzsc.lai.service.CfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CfgService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/22 17:52
 **/
@Service("cfgService")
public class CfgServiceImpl implements CfgService {
    @Autowired
    private CfgMapper cfgMapper;
    @Override
    public void save(Cfg cfg) {
        this.cfgMapper.insert(cfg);
    }
}
