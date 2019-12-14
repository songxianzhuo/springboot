package com.example.jtaatomikos.service.impl;

import com.example.jtaatomikos.dao.one.OneTestDao;
import com.example.jtaatomikos.dao.two.TwoTestDao;
import com.example.jtaatomikos.model.City;
import com.example.jtaatomikos.model.Province;
import com.example.jtaatomikos.model.TestForm;
import com.example.jtaatomikos.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName: TestService
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 15:49
 * @Version: 1.0
 */
@Slf4j
@Service
public class TestServiceImpl implements ITestService {

    @Resource
    private OneTestDao oneTestDao;
    @Resource
    private TwoTestDao twoTestDao;

    @Override
    public TestForm getAreaInfo(Integer provinceId,Integer cityId,Integer countyId){
        TestForm testForm = new TestForm();
        Province province = oneTestDao.getProvinceById(provinceId);
        City city = twoTestDao.getCityById(cityId);
        log.info("province:{}",province);
        log.info("city:{}",city);
        testForm.setProvince(province);
        testForm.setCity(city);
        testForm.setResult(province.getNumber().subtract(city.getNumber()));
        return testForm;
    }

    @Transactional
    @Override
    public void insertArea(TestForm testForm){
        Integer a = oneTestDao.addProvince(testForm.getProvince());
        Integer b = twoTestDao.addCity(testForm.getCity());
        System.out.println(a+b);
    }

    @Transactional
    @Override
    public void insertArea2(TestForm testForm){
        Integer a = oneTestDao.addProvince(testForm.getProvince());
        Integer b = twoTestDao.addCity(testForm.getCity());
        System.out.println(1/0);
        System.out.println(a+b);
    }

    @Transactional
    @Override
    public void addProvince(Province province){
        oneTestDao.addProvince(province);
    }


    @Transactional
    @Override
    public void addProvince2(Province province) {
        oneTestDao.addProvince(province);
        System.out.println(1/0);
    }

    @Transactional
    @Override
    public City getCityAfterAddProvince(Province province) {
        oneTestDao.addProvince(province);
        City city = twoTestDao.getCityById(110100);
        if(city == null){
            System.out.println(city.getName());
        }
        return city;
    }

    @Transactional
    @Override
    public City getCityAfterAddProvince2(Province province) {
        oneTestDao.addProvince(province);
        City city = twoTestDao.getCityById(110100);
        System.out.println(1/0);
        return city;
    }

    @Override
    public void updateArea(TestForm testForm) {
        Integer a = oneTestDao.updateProvince(testForm.getProvince());
        Integer b = twoTestDao.updateCity(testForm.getCity());
        System.out.println(a+b);
    }

    public static void main(String[] args) {
        BigDecimal one = BigDecimal.valueOf(150.01);
        one.add(BigDecimal.valueOf(100));
        BigDecimal two = BigDecimal.valueOf(50.6);
        Double oneD = 150.01;
        Double twoD = 50.6;
        System.out.println(one.subtract(two));
        System.out.println(one.add(two));
        System.out.println(oneD-twoD);

    }
}
