package org.lyflexi.debug_nacos;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-cloud-alibaba-practice
 * @Date: 2024/9/26 19:18
 */
@Component
@Slf4j
public class MockService {
    @Value("${custom.key}")
    String key;
    @PostConstruct
    public void init() {
        log.info("key= {}",key);
    }
}
