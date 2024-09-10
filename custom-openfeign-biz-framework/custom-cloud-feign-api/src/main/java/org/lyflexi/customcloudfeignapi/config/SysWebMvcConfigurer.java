package org.lyflexi.customcloudfeignapi.config;

import org.lyflexi.customcloudfeignapi.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ly
 * @Date: 2024/6/11 21:41
 */
@Configuration
@EnableWebMvc//开启自定义mvc注解
public class SysWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor())
                .addPathPatterns("/**") // 指定对所有请求进行拦截生效
                .excludePathPatterns("/excludePath"); // 排除某些路径不进行拦截
    }
}