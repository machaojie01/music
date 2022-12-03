package com.mcj.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author mcj
 * @date 2022/10/15 13:18
 * @email 2037612492@qq.com
 * @description swagger配置类
 */
@Configuration
@EnableOpenApi  // 开启swagger功能   swagger3.0.0版本以前使用@EnableSwagger2开启
public class SwaggerConfig {

    /**
     * RequestHandlerSelectors下的方法
     * 1.any() // 扫描所有，项目中的所有接口都会被扫描到
     * 2.none() // 不扫描接口
     * 3.withMethodAnnotation(final Class<? extends Annotation> annotation)  //通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
     * 4.withClassAnnotation(final Class<? extends Annotation> annotation)  // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
     * 5.basePackage(final String basePackage) // 根据包路径扫描接口
     *
     * PathSelectors下的方法
     * 1.any() // 任何请求都扫描
     * 2.none() // 任何请求都不扫描
     * 3.regex(final String pathRegex) // 通过正则表达式控制
     * 4.ant(final String antPattern) // 通过ant()控制
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)        // 是否开启，true开启，false隐藏，生产环境最好隐藏
                .select()   // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.mcj.music.controller"))   // 扫描的路径包,也就是会生成接口文档的包路径，设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .paths(PathSelectors.any()) // 指定路径处理，也就是会生成接口文档的路径，PathSelectors.any()代表所有的路径
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("音乐网站")                  // 设置文档标题
                .description("接口描述")            // 文档描述
                .termsOfServiceUrl("http://localhost:8888")          // 服务条款URL
                .version("V0.0.1")                 // 版本号
                .build();
    }


}
