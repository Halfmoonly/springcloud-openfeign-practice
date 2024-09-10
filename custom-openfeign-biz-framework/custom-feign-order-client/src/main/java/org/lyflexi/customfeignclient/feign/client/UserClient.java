package org.lyflexi.customfeignclient.feign.client;


import org.lyflexi.customcloudfeignapi.entity.User;
import org.lyflexi.customfeignclient.feign.config.UserConfiguration;
import org.lyflexi.customfeignclient.feign.interceptor.UserInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 14:13
 */
// http://localhost:9000/consumer/feign/user/get/1
@FeignClient(value = "custom-cloud-feign-server", contextId = "user", configuration = UserConfiguration.class)
public interface UserClient {

    @GetMapping(value = "/user/get/{id}")
    User getUserById(@PathVariable("id") Long id);
}