package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.UnionInfo;
import com.gzzsc.lai.provider.entity.UnionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnionInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int countByExample(UnionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int deleteByExample(UnionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int insert(UnionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int insertSelective(UnionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    List<UnionInfo> selectByExample(UnionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    UnionInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UnionInfo record, @Param("example") UnionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UnionInfo record, @Param("example") UnionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UnionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table union_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UnionInfo record);
}