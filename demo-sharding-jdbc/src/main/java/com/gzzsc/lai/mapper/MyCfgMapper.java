package com.gzzsc.lai.mapper;

import com.gzzsc.lai.entity.MyCfg;
import com.gzzsc.lai.entity.MyCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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