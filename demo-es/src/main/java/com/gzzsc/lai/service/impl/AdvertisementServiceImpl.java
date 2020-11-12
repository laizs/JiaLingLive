package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.Advertisement;
import com.gzzsc.lai.mapper.AdvertisementMapper;
import com.gzzsc.lai.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdvertisementServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/27 16:21
 **/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        int r= advertisementMapper.deleteByPrimaryKey(id);
        int a=1/0;
        return r;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Advertisement getById(String id) {
        return this.advertisementMapper.selectByPrimaryKey(id);
    }
}
