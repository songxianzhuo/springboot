package com.examplep.projectdemo.service;

import com.examplep.projectdemo.entity.bo.TestBO;
import com.examplep.projectdemo.entity.dto.TestDTO;
import com.examplep.projectdemo.entity.dto.TestQueryDTO;

import java.util.List;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
public interface ITestService {

    /**
     * add/update
     * @param testDTO
     * @return
     */
    void addOrUpdate(TestDTO testDTO);

    /**
     * 查询
     * @param id
     * @return
     */
    TestBO query(Integer id);

    /**
     * 分页查询
     * @param testQueryDTO
     * @return
     */
    List<TestBO> pageQuery(TestQueryDTO testQueryDTO);
}
