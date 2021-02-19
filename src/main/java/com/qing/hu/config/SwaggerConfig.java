package com.qing.hu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author sqh
 *
 */
@Configuration
@EnableSwagger2 //开启在线文档
public class SwaggerConfig {

    @Bean
    public Docket diyige() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.qing.hu.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("第一个");
    }
    /*
    @Bean
    public Docket dierge() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.qing.hu.xxxx"))
                .paths(PathSelectors.any())
                .build()
                .groupName("第二个");
    }
    */
   
    

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("石庆虎测试项目-API")
                .description("前端调用接口")
                .version("1.0")
                .build();
    }

}
