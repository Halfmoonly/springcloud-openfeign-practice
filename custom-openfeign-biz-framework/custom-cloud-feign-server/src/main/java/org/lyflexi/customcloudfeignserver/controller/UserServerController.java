package org.lyflexi.customcloudfeignserver.controller;

import io.micrometer.common.util.StringUtils;
import org.lyflexi.customcloudfeignapi.entity.User;
import org.lyflexi.customcloudfeignapi.entity.UserParam;
import org.lyflexi.customcloudfeignapi.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 14:09
 */
@RestController
public class UserServerController {

    private static final Logger log = LoggerFactory.getLogger(UserServerController.class);

    @GetMapping(value = "/user/get/{id}")
    public User getUserById(@PathVariable("id") Long id)
    {
        return new User(id, "user");
    }


    @PostMapping(value = "/user/search")
    public Result<List<User>> search(@RequestBody UserParam param)
    {
        log.info("feign传过来的请求体：{}",param);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("user" + i);
            users.add(user);
        }
        return new Result<>(users);
    }
}