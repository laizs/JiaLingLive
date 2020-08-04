package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.Advertisement;
import com.gzzsc.lai.provider.entity.AdvertisementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int countByExample(AdvertisementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int deleteByExample(AdvertisementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int insert(Advertisement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int insertSelective(Advertisement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    List<Advertisement> selectByExample(AdvertisementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    Advertisement selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Advertisement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Advertisement record);
}