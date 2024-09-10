package org.lyflexi.customfeignclient.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 22:06
 */

/*
 * feign拦截器
 * */
public class OrderInterceptor implements RequestInterceptor {
    public OrderInterceptor() {
        System.out.println("OrderInterceptor...");
    }
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("OrderInterceptor...");
    }
}
