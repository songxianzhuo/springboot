package com.example.jtaatomikos.dao.two;

import com.example.jtaatomikos.model.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @InterfaceName: TwoTestDao
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 14:57
 * @Version: 1.0
 */

public interface TwoTestDao {

    City getCityById(@Param("id") Integer id);

    Integer addCity(City city);

    @Select("select * from area_city where province_id = #{provinceId}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "province_id", column = "provinceId")
    })
    List<City> getCitysByProvinceId(@Param("provinceId") Integer provinceId);

    Integer updateCity(City city);
}
