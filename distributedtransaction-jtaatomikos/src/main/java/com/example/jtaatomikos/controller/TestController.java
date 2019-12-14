package com.example.jtaatomikos.controller;

import com.example.jtaatomikos.model.Province;
import com.example.jtaatomikos.model.TestForm;
import com.example.jtaatomikos.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 18:24
 * @Version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private ITestService testService;


    @GetMapping(value = "/getAreaInfo")
    public Object getAreaInfo(@RequestParam Integer provinceId,
                              @RequestParam Integer cityId,
                              @RequestParam Integer countyId){
        return testService.getAreaInfo(provinceId,cityId,countyId);
    }

    @PostMapping(value = "/addArea")
    public Object addArea(@RequestBody TestForm testForm){
        testService.insertArea(testForm);
        return true;
    }

    @PostMapping(value = "/addArea2")
    public Object addArea2(@RequestBody TestForm testForm){
        testService.insertArea2(testForm);
        return true;
    }

    @PostMapping(value = "/addProvince")
    public Object addProvince(@RequestBody Province province){
        testService.addProvince(province);
        return 1;
    }

    @PostMapping(value = "/addProvince2")
    public Object addProvince2(@RequestBody Province province){
        testService.addProvince2(province);
        return 1;
    }

}
