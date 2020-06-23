package com.gzzsc.lai.service;

import com.gzzsc.lai.entity.Cfg;

import java.util.List;

/**
 * @ClassName CfgService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/22 17:51
 **/
public interface CfgService {
    void save(Cfg cfg);

    /**
     *
     * @return
     */
    List<Cfg> getAll();

    /**
     *
     * @param id
     * @return
     */
    Cfg getById(Long id);

    /**
     *
     * @return
     */
    int deleteAll();
}
