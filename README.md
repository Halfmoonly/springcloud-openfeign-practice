# spring-cloud-alibaba-practice
环境说明：
- springboot3.0
- spring.cloud.alibaba 2022.0.0.0-RC2
- spring.cloud 2022.0.0

本仓库涵盖以下源码调试:
- openfeign代理原理
- openfeign循环依赖事故模拟与解决

```shell
Description:

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  lyWebMvcConfig (field org.lyflexi.feignclient.feign.client.UserClient org.lyflexi.feignclient.config.LyWebMvcConfig.userClient)
↑     ↓
|  org.lyflexi.feignclient.feign.client.UserClient
↑     ↓
|  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.

Disconnected from the target VM, address: '127.0.0.1:51151', transport: 'socket'

Process finished with exit code 1
```