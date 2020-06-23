package com.gzzsc.lai.mapper;

import com.gzzsc.lai.entity.Cfg;
import com.gzzsc.lai.entity.CfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CfgMapper {
    int countByExample(CfgExample example);

    int deleteByExample(CfgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Cfg record);

    int insertSelective(Cfg record);

    List<Cfg> selectByExample(CfgExample example);

    Cfg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Cfg record, @Param("example") CfgExample example);

    int updateByExample(@Param("record") Cfg record, @Param("example") CfgExample example);

    int updateByPrimaryKeySelective(Cfg record);

    int updateByPrimaryKey(Cfg record);
}