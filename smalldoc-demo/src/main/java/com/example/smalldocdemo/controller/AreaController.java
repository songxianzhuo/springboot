package com.example.smalldocdemo.controller;

import com.example.smalldocdemo.model.vo.CityVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域相关接口
 * @Author: SONG
 * @Date: 2019/12/25
 * @Version: 1.0.0
 **/
@RestController
public class AreaController {

    /**
     * 霍去病城市列表
     * @return 城市对象集合
     */
    @GetMapping(value = "/getCityList")
    public List<CityVO> getCityList(){
        List<CityVO> cityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CityVO cityVO = new CityVO();
            cityVO.setId(i);
            cityVO.setName("城市" + i);
            cityVO.setDesc("简介"+ i);
            cityList.add(cityVO);
        }
        return cityList;
    }
}
