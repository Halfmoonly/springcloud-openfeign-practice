package org.lyflexi.debug_sentinel.testBySemaphore;

/**
 * @Author: ly
 * @Date: 2024/2/24 19:50
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class SemaphoreAop {

    //这里需要注意了，这个是将自己自定义注解作为切点的根据，路径一定要写正确了

    @Pointcut(value = "@annotation(org.lyflexi.debug_sentinel.testBySemaphore.SemaphoreDoc)")
    public void semaphoreDoc() {

    }
    //限流池
    private static  Map<String, Semaphore> map=new ConcurrentHashMap<>();


    @Around("semaphoreDoc()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        SemaphoreDoc annotation  = signature.getMethod().getAnnotation(SemaphoreDoc.class);
        int blockingTime = annotation.blockingTime();
        int limit = annotation.limit();
        String key = annotation.key();


        StringBuilder name = new StringBuilder(key+signature.getMethod().getName());//方法名
        for (String parameterName : signature.getParameterNames()) {
            name.append(parameterName);
        }

        Semaphore semaphore = map.get(name.toString());
        if (semaphore == null) {
            Semaphore semaphore1 = new Semaphore(limit);
            map.put(name.toString(),semaphore1);
            semaphore=semaphore1;
        }

        try {


            //获取令牌
            boolean b = semaphore.tryAcquire(blockingTime, TimeUnit.SECONDS);
            if (b) {//如果拿到令牌了那么执行方法
                try {
                    res = joinPoint.proceed();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                //在一定时间内拿不到令牌那么就访问失败
                throw  new Exception("访问超时,目前请求人数过多请稍后在试");
            }

        } finally {
            //释放令牌，腾出位置
            semaphore.release();
        }

        return  res;
    }

}
