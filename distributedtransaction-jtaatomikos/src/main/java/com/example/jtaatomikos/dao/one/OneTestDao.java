package com.example.jtaatomikos.dao.one;

import com.example.jtaatomikos.model.Province;
import org.apache.ibatis.annotations.*;

/**
 * @InterfaceName: OneTestDao
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 14:56
 * @Version: 1.0
 */

public interface OneTestDao {

    Province getProvinceById(@Param("id") Integer id);

    Integer addProvince(Province province);

    Integer updateProvince(Province province);
}
