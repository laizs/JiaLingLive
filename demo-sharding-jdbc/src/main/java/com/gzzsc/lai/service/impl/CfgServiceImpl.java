package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.provider.entity.MyCfg;
import com.gzzsc.lai.mapper.MyCfgMapper;
import com.gzzsc.lai.service.CfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CfgService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/22 17:52
 **/
@SuppressWarnings("ALL")
@Service("cfgService")
public class CfgServiceImpl implements CfgService {
    @Autowired
    private MyCfgMapper myCfgMapper;
    @Override
    public void save(MyCfg cfg) {
        this.myCfgMapper.insertSelective(cfg);
    }

    /**
     * @return
     */
    @Override
    public List<MyCfg> getAll() {
        return this.myCfgMapper.selectByExample(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MyCfg getById(Long id) {
        return this.myCfgMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     */
    @Override
    public int deleteAll() {
        return this.myCfgMapper.deleteByExample(null);
    }
}
