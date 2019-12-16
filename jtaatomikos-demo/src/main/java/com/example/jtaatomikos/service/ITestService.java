package com.example.jtaatomikos.service;

import com.example.jtaatomikos.model.City;
import com.example.jtaatomikos.model.Province;
import com.example.jtaatomikos.model.TestForm;

/**
 * @InterfaceName: ITestService
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/19 15:15
 * @Version: 1.0
 */
public interface ITestService {

    TestForm getAreaInfo(Integer provinceId, Integer cityId, Integer countyId);

    void insertArea(TestForm testForm);

    void insertArea2(TestForm testForm);

    void addProvince(Province province);

    void addProvince2(Province province);

    City getCityAfterAddProvince(Province province);

    City getCityAfterAddProvince2(Province province);

    void updateArea(TestForm testForm);

}
