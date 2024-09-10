package org.lyflexi.customfeignclient.feign.interceptor;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lyflexi.customcloudfeignapi.constant.LyFlexiConstant;
import org.lyflexi.customcloudfeignapi.entity.UserParam;
import org.lyflexi.customcloudfeignapi.holder.ProjectContextHolder;

import java.nio.charset.StandardCharsets;

/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/7/27 22:06
 */

/*
 * feign拦截器
 * */
@Slf4j
public class UserInterceptor implements RequestInterceptor {
    public UserInterceptor() {
        log.info("UserInterceptor...");
    }

    @Override
    public void apply(RequestTemplate template) {
        log.info("UserInterceptor...");

        String jsonString = builderHeader();
        template.header(LyFlexiConstant.Feign_User_Info, jsonString);

        // 判断请求类型是否为POST,只有当请求方法为 POST 时，才执行设置请求体的逻辑。否则会报405异常
        if ("POST".equalsIgnoreCase(template.method())) {
            String feignBodyJson = builderBody();
            template.body(feignBodyJson.getBytes(), StandardCharsets.UTF_8);
        }
    }

    private static @NotNull String builderBody() {
        // 创建并设置请求体对象
        UserParam feignBody = new UserParam();
        feignBody.setField1("value1");
        feignBody.setField2("value2");
        // 将请求体转换为 JSON 字符串并设置为请求体
        return JSON.toJSONString(feignBody);
    }

    private static @NotNull String builderHeader() {
        return JSON.toJSONString(ProjectContextHolder.getInstance().getContext());
    }
}
