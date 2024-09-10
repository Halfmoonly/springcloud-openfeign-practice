package org.lyflexi.customcloudfeignapi.interceptor;

import com.alibaba.fastjson.JSON;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.lyflexi.customcloudfeignapi.constant.LyFlexiConstant;
import org.lyflexi.customcloudfeignapi.holder.LoginUserVo;
import org.lyflexi.customcloudfeignapi.holder.ProjectContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: ly
 * @Date: 2024/6/11 21:40
 */
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle: 请求处理之前，进行日志记录");
        log.info("请求打印：{}",request.getRequestURI());
        //普通PostMan请求头拦截//解析出登录用户信息，并设置上下文
        ProjectContextHolder contextHolder = ProjectContextHolder.getInstance();
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUserName("mockUser");
        loginUserVo.setUserCode("mockUser");
        loginUserVo.setFactoryCode("mockFactory");
        contextHolder.setContext(loginUserVo);

        //服务间feign请求头拦截
        String feignHeader = request.getHeader(LyFlexiConstant.Feign_User_Info);
        if (StringUtils.isNotBlank(feignHeader)){
            loginUserVo = JSON.parseObject(feignHeader, LoginUserVo.class);
            contextHolder.setContext(loginUserVo);
        }
        return true; // 允许请求继续
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle: 请求处理之后，视图渲染之前，可以在这里修改响应数据");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletion: 整个请求完成，可以进行资源清理");
        ProjectContextHolder.getInstance().clear();
    }
}