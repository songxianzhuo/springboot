package com.example.resttemplateserver.controller;

import com.example.resttemplateserver.model.Article;
import com.example.resttemplateserver.model.ArticleParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/10/31 11:51
 * @Version: 1.0
 */
@RestController
public class TestController {

    /**
     * @RequestMapping
     * 是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
     * 有6个属性
     *
     * value：指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）
     * method：指定请求的method类型， GET、POST、PUT、DELETE等；
     *
     * consumes：指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
     * produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
     *
     * params：指定request中必须包含某些参数值是，才让该方法处理。
     * headers：指定request中必须包含某些指定的header值，才能让该方法处理请求。
     * @return
     */


    /**
     * @RequestMapping的默认请求方法是Get
     * @RequestMapping("/test") 和 @RequestMapping(value = "/test") 和 @RequestMapping(path = "/test") 效果一样
     * @return
     */
    @RequestMapping("/test")
    public Object test(){
        Article article = result();
        article.setType("testGet");
        return article;
    }

    /**
     * 占位符映射
     * url中的占位符名称要和@PathVariable的name值或value值保持一致，没有指定@PathVariable的name值或value值。则和方法参数名保持一致
     * @PathVariable 不能为空，不能设置默认值，因为null对于url是无意义的。
     * 对于@PathVariable注解，如果有required = false的情况，则该方法需要配置多个url映射
     * @return
     */
    @RequestMapping(value = {"/testPath/{mcnId}/{platId}","/testPath/{mcnId}"})
    public Object testPath(HttpServletRequest request,
                           @PathVariable("mcnId") String mcnid,
                           @PathVariable(required = false) Integer platId){
        System.out.println("url:" + request.getRequestURI());
        System.out.println("mcnid:" + mcnid);
        System.out.println("platId:" + platId);
        Article article = result();
        article.setMcnid(mcnid);
        article.setPlatId(platId);
        article.setType("testGetPath");
        return article;
    }

    /**
     * @RequestParam注解，如果required=true,你有没有传参，如果设置了defaultValue属性，也会成功请求，不会报错
     * @return
     */
    @GetMapping(value = "/testGetParam")
    public Object testGetParam(HttpServletRequest request,
                               @RequestParam String mcnid,
                               @RequestParam(defaultValue = "10") Integer platId,
                               @RequestParam(required = false) String type){
        System.out.println("url:" + request.getRequestURI());
        System.out.println("mcnid:" + mcnid);
        System.out.println("platId:" + platId);
        System.out.println("type:" + type);
        Article article = result();
        article.setMcnid(mcnid);
        article.setPlatId(platId);
        article.setType(type);
        return article;
    }

    /**
     * @PostMapping
     * consumes没有设置则对请求类型没有限制
     * 设置值后，只有接受对应的请求类型
     * @return
     */
    @PostMapping(value = "/testPostParam",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object testPostParam(HttpServletRequest request,
                                @RequestParam String mcnid,
                                @RequestParam(defaultValue = "10") Integer platId,
                                @RequestParam(required = false) String type){
        System.out.println("url:" + request.getRequestURI());
        System.out.println("mcnid:" + mcnid);
        System.out.println("platId:" + platId);
        System.out.println("type:" + type);
        Article article = result();
        article.setMcnid(mcnid);
        article.setPlatId(platId);
        article.setType(type);
        return article;
    }


    /**
     * @PostMapping
     * @param articleParam
     * @return
     */
    @PostMapping(value = "/testPostBody")
    public Object testPostBody(@RequestBody ArticleParam articleParam){
        System.out.println(articleParam.toString());
        Article article = result();
        article.setMcnid(articleParam.getMcnid());
        article.setPlatId(articleParam.getPlatId());
        article.setType(articleParam.getType());
        return article;
    }

    /**
     * @PostMapping 如果设置了consumes，client端需要和server端保持一致
     * @param articleParam
     * @return
     */
    @PostMapping(value = "/testPostBody2",consumes = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public Object testPostBody2(@RequestBody ArticleParam articleParam){
        System.out.println(articleParam.toString());
        Article article = result();
        article.setMcnid(articleParam.getMcnid());
        article.setPlatId(articleParam.getPlatId());
        article.setType(articleParam.getType());
        return article;
    }

    /**
     *
     * @param articleParam
     * @param title
     * @return
     */
    @PostMapping(value = "/testPostBodyParam")
    public Object testPostBodyParam(@RequestBody ArticleParam articleParam,@RequestParam String title){
        System.out.println(articleParam.toString());
        System.out.println("title:" + title);
        Article article = result();
        article.setMcnid(articleParam.getMcnid());
        article.setPlatId(articleParam.getPlatId());
        article.setType(articleParam.getType());
        article.setTittle(title);
        return article;
    }

    /**
     * @RequestHeader
     * post方式可以拿到header的参数，get方式拿不到
     * @return
     */
    @RequestMapping(value = "/testParamHeader")
    public Object testParamHeader(@RequestParam String mcnid,@RequestHeader Integer platId){
        System.out.println("mcnid:" + mcnid);
        System.out.println("platId:" + platId);
        Article article = result();
        article.setMcnid(mcnid);
        article.setPlatId(platId);
        article.setType("testPostParamHeader");
        return article;
    }


    /**
     * 生成返回结果
     * @return
     */
    private Article result(){
        Article article = new Article();
        article.setPlatId(10);
        article.setMcnid("mcn0000");
        article.setPlatform("toutiao");
        article.setTittle("全国各族人民共同庆祝中华人民共和国成立70周年");
        article.setPublish_time("2019-10-01 00:00:00");
        return article;
    }
}
