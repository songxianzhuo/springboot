package com.example.jtaatomikos;

import com.example.jtaatomikos.model.City;
import com.example.jtaatomikos.model.County;
import com.example.jtaatomikos.model.Province;
import com.example.jtaatomikos.model.TestForm;
import com.example.jtaatomikos.service.ITestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * springboot
 * 单元注解@Test加载的类
 * 2.1版本及以前：org.junit.Test
 * 2.2版本及以后：org.junit.jupiter.api.Test
 */
@SpringBootTest
public class JtaatomikosApplicationTests {

    @Autowired
    private ITestService testService;

    @Test
    public void contextLoads() {
        System.out.println(132456);
    }

    /**
     * 分布式事务获取数据
     */
    @Test
    public void getAreaInfo(){
        System.out.println(testService.getAreaInfo(110000,110100,110101));
    }

    /**
     * 分布式事务添加数据
     */
    @Test
    public void testAtomikos() {
        TestForm testForm = new TestForm();
        Province province = new Province();
        province.setId(140001);
        province.setName("河北省");
        province.setNumber(BigDecimal.valueOf(1000));
        City city = new City();
        city.setId(140102);
        city.setName("石家庄市");
        city.setNumber(BigDecimal.valueOf(2000));
        testForm.setProvince(province);
        testForm.setCity(city);
        //正常
        //testService.insertArea(testForm);
        //异常
        testService.insertArea2(testForm);
    }

    /**
     * 单数据源
     */
    @Test
    public void testAddProvince(){
        Province province = new Province();
        province.setId(130000);
        province.setName("河北省");
        //正常
        testService.addProvince(province);
        //异常
        testService.addProvince2(province);
    }

    @Test
    public void testGetCityAfterAddProvince(){
        Province province = new Province();
        province.setId(170000);
        province.setName("江苏省");
        //正常
        City city = testService.getCityAfterAddProvince(province);
        //异常
        //City city = testService.getCityAfterAddProvince2(province);
        System.out.println(city);
    }

    @Test
    public void testUpdateArea(){
        TestForm testForm = new TestForm();
        Province province = new Province();
        province.setId(110000);
        province.setName("河北省");
        province.setNumber(BigDecimal.valueOf(2000));
        City city = new City();
        city.setId(110100);
        city.setName("石家庄市");
        city.setNumber(BigDecimal.valueOf(3000));
        testForm.setProvince(province);
        testForm.setCity(city);
        testService.updateArea(testForm);
    }

}
