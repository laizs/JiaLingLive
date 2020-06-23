package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.Cfg;
import com.gzzsc.lai.mapper.CfgMapper;
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
    private CfgMapper cfgMapper;
    @Override
    public void save(Cfg cfg) {
        this.cfgMapper.insert(cfg);
    }

    /**
     * @return
     */
    @Override
    public List<Cfg> getAll() {
        return this.cfgMapper.selectByExample(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Cfg getById(Long id) {
        return this.cfgMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     */
    @Override
    public int deleteAll() {
        return this.cfgMapper.deleteByExample(null);
    }
}
