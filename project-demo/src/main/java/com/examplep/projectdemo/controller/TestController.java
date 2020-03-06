package com.examplep.projectdemo.controller;

import com.examplep.projectdemo.entity.bo.TestBO;
import com.examplep.projectdemo.entity.dto.TestDTO;
import com.examplep.projectdemo.entity.dto.TestQueryDTO;
import com.examplep.projectdemo.entity.feo.TestFEO;
import com.examplep.projectdemo.entity.feo.TestQueryFEO;
import com.examplep.projectdemo.entity.vo.TestVO;
import com.examplep.projectdemo.service.ITestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@RestController
public class TestController {

    private ITestService testService;

    @Autowired
    public TestController(ITestService testService){
        this.testService = testService;
    }

    /**
     * 项目分层原则
     * Service层最好不要将DAO层查询出的PO、DO对象直接暴露给Controller层
     * Service层最好不要把前端给的FEO直接暴露给DAO层
     *
     */

    @PostMapping(value = "/addOrUpdate")
    public Object addOrUpdate(@RequestBody TestFEO testFEO){
        TestDTO testDTO = new TestDTO();
        BeanUtils.copyProperties(testFEO,testDTO);
        testService.addOrUpdate(testDTO);
        return "ok";
    }

    @GetMapping(value = "/query")
    public Object query(@RequestParam Integer id){
        TestVO testVO = new TestVO();
        TestBO testBO = testService.query(id);
        BeanUtils.copyProperties(testBO,testVO);
        return testVO;
    }

    @PostMapping(value = "/pageQuery")
    public Object pageQuery(@RequestBody TestQueryFEO testQueryFEO){
        TestQueryDTO testQueryDTO = new TestQueryDTO();
        BeanUtils.copyProperties(testQueryFEO,testQueryDTO);
        List<TestBO> result = testService.pageQuery(testQueryDTO);
        return result;
    }
}
