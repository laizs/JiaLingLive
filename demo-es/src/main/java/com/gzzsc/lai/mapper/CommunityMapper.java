package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.Community;
import com.gzzsc.lai.provider.entity.CommunityExample;
import com.gzzsc.lai.provider.entity.CommunityWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommunityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int countByExample(CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int deleteByExample(CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int insert(CommunityWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int insertSelective(CommunityWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    List<CommunityWithBLOBs> selectByExampleWithBLOBs(CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    List<Community> selectByExample(CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    CommunityWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CommunityWithBLOBs record, @Param("example") CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") CommunityWithBLOBs record, @Param("example") CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Community record, @Param("example") CommunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CommunityWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(CommunityWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Community record);
}