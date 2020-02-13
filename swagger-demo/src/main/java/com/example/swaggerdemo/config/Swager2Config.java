package com.example.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: Swager2Config
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/23 18:18
 * @Version: 1.0
 */
@EnableSwagger2
@Configuration
public class Swager2Config {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 接口文档描述
                .apiInfo(apiInfo())
                .select()
                // Controller所在包(必须新建包)，可以精确到具体的controller，这样一个controller一个文档
                .apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 接口文档的名字
                .title("demo api")
                // 接口文档的描述
                .description("demo api接口文档")
                // 服务条款网址
                //.termsOfServiceUrl("http://localhost/")
                // 接口文档的版本
                .version("1.0.0")
                // 接口文档维护联系信息
                .contact(new Contact("宋献卓", "", "happysong8783@163.com"))
                .build();
    }
}
