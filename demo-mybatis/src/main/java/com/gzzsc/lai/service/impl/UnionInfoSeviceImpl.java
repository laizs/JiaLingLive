package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.provider.entity.UnionInfo;
import com.gzzsc.lai.mapper.UnionInfoMapper;
import com.gzzsc.lai.service.UnionInfoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className UnionInfoSeviceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/23 17:50
 **/
@SuppressWarnings("ALL")
@Service("unionInfoSevice")
public class UnionInfoSeviceImpl implements UnionInfoSevice {
    @Autowired
    private UnionInfoMapper unionInfoMapper;
    @Override
    public void doTestOneCached() {
        System.out.println("--第一次查询--");
        unionInfoMapper.selectByPrimaryKey("0");
        System.out.println("--第二次查询--");
        unionInfoMapper.selectByPrimaryKey("0");

    }

    @Override
    public void doTestSecondCached() {
        System.out.println("--二级缓存测试-第一次查询--");
        unionInfoMapper.selectByPrimaryKey("1");
        System.out.println("--二级缓存测试-第二次查询--");
        unionInfoMapper.selectByPrimaryKey("1");
    }

    @Override
    public void update(String id) {
      UnionInfo u= unionInfoMapper.selectByPrimaryKey(id);
      this.unionInfoMapper.updateByPrimaryKey(u);
    }
}
