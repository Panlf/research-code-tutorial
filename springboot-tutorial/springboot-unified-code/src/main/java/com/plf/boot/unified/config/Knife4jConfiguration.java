package com.plf.boot.unified.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author panlf
 * @date 2021/7/30
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    @Bean(value = "knife4jApi2")
    public Docket knife4jApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("Swagger UI")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("plf","","liangfeng_pan@163.com"))
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("1.0.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.plf.boot.unified.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
