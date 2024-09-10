package org.lyflexi.customfeignclient.controller;


import org.lyflexi.customcloudfeignapi.entity.User;
import org.lyflexi.customcloudfeignapi.result.Result;
import org.lyflexi.customfeignclient.feign.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 14:13
 */
//curl http://localhost:9000/consumer/feign/user/get/1
@RestController
public class UserClientController {

    @Autowired
    private UserClient userClient;

    @GetMapping(value = "/consumer/feign/user/get/{id}")
    public Result<User> getUserById(@PathVariable("id") Long id)
    {
        User user = userClient.getUserById(id);
        return new Result<>(user);
    }
}