package com.examplep.projectdemo.service.impl;

import com.examplep.projectdemo.entity.bo.TestBO;
import com.examplep.projectdemo.entity.dto.TestDTO;
import com.examplep.projectdemo.entity.dto.TestQueryDTO;
import com.examplep.projectdemo.entity.po.TestDO;
import com.examplep.projectdemo.entity.po.TestPO;
import com.examplep.projectdemo.service.ITestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Service
public class TestServiceImpl implements ITestService {

    @Override
    public void addOrUpdate(TestDTO testDTO) {
        if(-1 == testDTO.getId()){
            // insert
            TestPO testPO = new TestPO();
            BeanUtils.copyProperties(testDTO,testPO);
        }else{
            // update
            TestPO testPO = new TestPO();
            BeanUtils.copyProperties(testDTO,testPO);
            // 关联更新
            TestDO testDO = new TestDO();
            BeanUtils.copyProperties(testDTO,testDO);
        }
    }

    @Override
    public TestBO query(Integer id) {
        TestBO testBO = new TestBO();
        // 单表查询,调用Dao层省略
        TestPO testPO = new TestPO();
        // 多表联查,调用Dao层省略
        TestDO testDO = new TestDO();
        BeanUtils.copyProperties(testBO,testPO);
        return testBO;
    }

    @Override
    public List<TestBO> pageQuery(TestQueryDTO testQueryDTO) {
        // 通过testQueryDTO查询出的TestPO集合
        List<TestPO> list = new ArrayList<>();
        // 返回的TestBO集合
        List<TestBO> boList = new ArrayList<>();
        list.forEach(testPO -> {
            TestBO testBO = new TestBO();
            BeanUtils.copyProperties(testPO,testBO);
            boList.add(testBO);
        });
        return boList;
    }
}
