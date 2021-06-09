package com.yuki.springbootjspmavenexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置 URL 路径
        registry.addResourceHandler("/test/**")
                // 设置资源路径， https 或者 file
                .addResourceLocations("file:E:/TMP/IMG/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }



}
