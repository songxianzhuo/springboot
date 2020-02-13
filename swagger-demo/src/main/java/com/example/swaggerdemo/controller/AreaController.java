package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.model.vo.CityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CommonController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/23 19:28
 * @Version: 1.0
 */
@Api(tags = "区域相关接口")
@RestController
public class AreaController {


    @ApiOperation(value = "获取城市列表",notes = "城市列表")
    @GetMapping(value = "/getCityList")
    public Object getCityList(){
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
