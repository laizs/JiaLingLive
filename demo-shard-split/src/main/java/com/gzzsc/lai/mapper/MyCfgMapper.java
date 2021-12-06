package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.MyCfg;
import com.gzzsc.lai.provider.entity.MyCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCfgMapper {
    int countByExample(MyCfgExample example);

    int deleteByExample(MyCfgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyCfg record);

    int insertSelective(MyCfg record);

    List<MyCfg> selectByExample(MyCfgExample example);

    MyCfg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyCfg record, @Param("example") MyCfgExample example);

    int updateByExample(@Param("record") MyCfg record, @Param("example") MyCfgExample example);

    int updateByPrimaryKeySelective(MyCfg record);

    int updateByPrimaryKey(MyCfg record);
}