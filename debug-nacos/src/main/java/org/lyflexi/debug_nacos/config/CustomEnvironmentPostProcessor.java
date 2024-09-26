package org.lyflexi.debug_nacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-cloud-alibaba-practice
 * @Date: 2024/9/26 13:18
 */
@Slf4j
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {


    public static final String NACOS_DISCOVERY_NAMESPACE_KEY = "spring.cloud.nacos.discovery.namespace";
    public static final String NACOS_CONFIG_NAMESPACE_KEY = "spring.cloud.nacos.config.namespace";
    public static final String NACOS_NAMESPACE_VALUE_TEST = "4fa94e78-d9c0-460b-920b-17acaabc00d9";

    public static final String APPLICATION_NAME_KEY = "spring.application.name";
    public static final String APPLICATION_NAME_VALUE = "debug-nacos";
    public static final String APPLICATION_NAME_VALUE_LOCAL = "local-debug-nacos";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        if (isWindows()){
            HashMap<String, Object> map = new HashMap<String, Object>();
            String ipSuffix = extractIpAddress();
            map.put(NACOS_DISCOVERY_NAMESPACE_KEY, NACOS_NAMESPACE_VALUE_TEST);
            map.put(NACOS_CONFIG_NAMESPACE_KEY, NACOS_NAMESPACE_VALUE_TEST);
            if (APPLICATION_NAME_VALUE.equals(environment.getProperty(APPLICATION_NAME_KEY))){
                map.put(APPLICATION_NAME_KEY,APPLICATION_NAME_VALUE_LOCAL+SysConstant.SELECTOR_SPLIT+ipSuffix);
            }
            environment.getPropertySources().addLast(new MyPropertySource(map));
        }
    }
    private boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.contains("win");
    }
    private static class MyPropertySource extends MapPropertySource {
        public MyPropertySource(Map<String, Object> source) {
            super("myPropertySource", source);
        }
    }

    private void getAllProperties(ConfigurableEnvironment environment){
        // 获取所有的PropertySource
        for (PropertySource<?> source : environment.getPropertySources()) {
            if (source instanceof MapPropertySource) {
                Map<String, Object> map = ((MapPropertySource) source).getSource();
                // 打印所有的配置项
                map.forEach((key, value) -> {
                    System.out.println("Config item: " + key + " = " + value);
                });
            }
        }
    }

    private String extractIpAddress(){
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String ip = localHost.getHostAddress();
        return ip.split("\\.")[3];
    }
}