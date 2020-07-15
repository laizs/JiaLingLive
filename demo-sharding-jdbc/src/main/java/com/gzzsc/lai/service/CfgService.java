package com.gzzsc.lai.service;

import com.gzzsc.lai.entity.MyCfg;

import java.util.List;

/**
 * @ClassName CfgService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/22 17:51
 **/
public interface CfgService {
    void save(MyCfg cfg);

    /**
     *
     * @return
     */
    List<MyCfg> getAll();

    /**
     *
     * @param id
     * @return
     */
    MyCfg getById(Long id);

    /**
     *
     * @return
     */
    int deleteAll();
}
