package com.example.resttemplateclient.service;


import com.example.resttemplateclient.ResttemplateclientApplicationTests;
import com.example.resttemplateclient.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestService
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/10/30 16:13
 * @Version: 1.0
 */
public class TestService extends ResttemplateclientApplicationTests {

    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8001";

    /**
     * RestTemplate的*ForEntity和*ForObject都有三种传参方式
     */

    @Test
    public void testGet(){
        url = url + "/test";
        // getForEntity
        ResponseEntity<Article> responseEntity = restTemplate.getForEntity(url, Article.class);
        Article article = responseEntity.getBody();
        System.out.println(article.toString());
        //getForObject
        Article article2 = restTemplate.getForObject(url, Article.class);
        System.out.println(article2.toString());
    }


    @Test
    public void testGetPath(){
        url = url + "/testPath/{mcnId}";
        //map
        Map<String,Object> map = new HashMap<>();
        map.put("mcnId","mcn000726");
        /**
         * 缺少参数
         */
        // getForEntity map
        System.out.println("getForEntity 缺少参数 map");
        ResponseEntity<Article> responseEntity = restTemplate.getForEntity(url,Article.class,map);
        Article article1 = responseEntity.getBody();
        System.out.println(article1.toString());
        // getForEntity param
        System.out.println("getForEntity 缺少参数 param");
        ResponseEntity<Article> responseEntity2 = restTemplate.getForEntity(url,Article.class,"mcn0001");
        Article article2 = responseEntity2.getBody();
        System.out.println(article2.toString());
        /**
         * 参数完整
         */
        url = url + "/{platId}";
        map.put("platId",10);
        //getForObject map
        System.out.println("getForObject 参数完整 map");
        Article article3 = restTemplate.getForObject(url, Article.class,map);
        System.out.println(article3.toString());
        //getForObject param
        System.out.println("getForObject 参数完整 param");
        Article article4 = restTemplate.getForObject(url, Article.class,"mcn0002",10);
        System.out.println(article4.toString());
    }

    @Test
    public void testGetParam(){
        url = url + "/testGetParam?mcnid={mcnid}";
        //map
        Map<String,Object> map = new HashMap<>();
        map.put("mcnid","mcn000726");
        //getForObject map
        Article article = restTemplate.getForObject(url, Article.class,map);
        System.out.println(article.toString());
        url = url + "&type={type}&platId={platId}";
        //getForEntity param 注意参数值和占位符顺序一致
        ResponseEntity<Article> responseEntity2 = restTemplate.getForEntity(url,Article.class,"testGetParam","mcn0000",11);
        Article article2 = responseEntity2.getBody();
        System.out.println(article2.toString());
    }


    /**
     * post param请求时
     * client端要用LinkedMultiValueMap来封装参数
     * client端和server端的请求类型需保持一致,
     * 如果server是默认的请求类型，
     *      client可以不需要配置HttpHeaders和HttpEntity，直接用LinkedMultiValueMap传参就可以
     *      client如果配置了HttpHeaders,HttpHeaders可以不用设置ContentType属性
     */
    @Test
    public void testPostParam(){
        url = url + "/testPostParam";
        //MultiValueMap
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("mcnid","mcn0000");
        //postForEntity MultiValueMap,service
        ResponseEntity<Article> responseEntity = restTemplate.postForEntity(url,params,Article.class);
        Article article = responseEntity.getBody();
        System.out.println(article.toString());
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //设置请求类型，默认是MediaType.APPLICATION_FORM_URLENCODED
        //headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //设置请求体
        HttpEntity httpEntity = new HttpEntity(params,headers);
        //postForObject httpEntity
        params.add("platId",11);
        params.add("type","testPostParam");
        Article article2 = restTemplate.postForObject(url,httpEntity,Article.class);
        System.out.println(article2.toString());
    }

    /**
     * post body请求时
     * client端和server端的请求类型需保持一致,
     * 如果server是默认的请求类型，
     *      client可以不需要配置HttpHeaders和HttpEntity,直接用map传参
     *      client如果配置了HttpHeaders,HttpHeaders可以不用设置ContentType属性
     */
    @Test
    public void testPostBody(){
        url = url + "/testPostBody";
        //HashMap
        Map<String,Object> map = new HashMap<>();
        map.put("mcnid","mcn0001");
        map.put("platId",10);
        map.put("type","testPostBody1");
        Article article = restTemplate.postForObject(url,map,Article.class);
//        Article article = responseEntity.getBody();
        System.out.println(article.toString());
//        url = url + "2";
//        map.put("mcnid","mcn0002");
//        map.put("platId",11);
//        map.put("type","testPostBody2");
//        //设置请求头
//        HttpHeaders headers = new HttpHeaders();
//        //设置请求类型，默认是MediaType.APPLICATION_FORM_URLENCODED
//        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
//        HttpEntity httpEntity = new HttpEntity(map,headers);
//        Article article2 = restTemplate.postForObject(url,httpEntity,Article.class);
//        System.out.println(article2.toString());
    }

    /**
     * post body+param
     * 这种情况下，需要用占位符将param拼接到请求的url上面
     */
    @Test
    public void testPostBodyParam(){
        url = url + "/testPostBodyParam?title={title}";
        //HashMap
        Map<String,Object> map = new HashMap<>();
        map.put("mcnid","mcn0001");
        map.put("platId",10);
        map.put("type","testPostBody1");
        Map<String,Object> other = new HashMap<>();
        other.put("title","我是一只小小鸟");
        ResponseEntity<Article> responseEntity = restTemplate.postForEntity(url,map,Article.class,other);
        Article article = responseEntity.getBody();
        System.out.println(article.toString());
        map.put("mcnid","mcn0002");
        map.put("platId",11);
        map.put("type","testPostBody2");
        Article article2 = restTemplate.postForObject(url,map,Article.class,"我是一只小小鸟");
        System.out.println(article2.toString());
    }

    /**
     * 通过HttpHeaders传递参数
     */
    @Test
    public void testParamHeader(){
        url = url + "/testParamHeader";
        //post
        //MultiValueMap
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("mcnid","mcn0000");
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("platId","10");
        //设置请求体
        HttpEntity httpEntity = new HttpEntity(params,headers);
        //postForObject httpEntity
        Article article = restTemplate.postForObject(url,httpEntity,Article.class);
        System.out.println(article.toString());
    }

    @Test
    public void testtest(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.forEach(s -> {
            System.out.println(s);
            System.out.println(12346798);
        });
    }
}
