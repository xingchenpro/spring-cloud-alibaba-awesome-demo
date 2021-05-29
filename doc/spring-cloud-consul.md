### spring-cloud-consul

### 一、简介

### 二、安装 consul  环境

consul 和 eureka 不同，需要单独安装，这里我们使用 docker 进行快速安装。

1、docker 安装 consul

```
docker run -d -p 8500:8500 --restart=always --name=consul consul:latest agent -server -bootstrap -ui -node=1 -client='0.0.0.0'
```

2、访问 consul ，可以看到管理页面

```
http://xxx.57.173.162:8500/ui/
```

3、手动删除无效服务，自动剔除需要在 yml 配置

```
PUT http://123.57.173.162:8500/v1/agent/service/deregister/service-name
```



### 三、springcloud 整合 consul

1、添加依赖

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
```

2、yml 文件配置

```
server:
  port: 8781

spring:
  application:
    name: stardust-producer
  cloud:
    consul:
      host: 123.57.173.162
      port: 8500
      discovery:
        # 显示客户端 ip
        prefer-ip-address: true
        #服务名称
        serviceName: ${spring.application.name}
        # 开启健康检查
        heartbeat:
          enabled: true
        #健康检查时间间隔
        health-check-interval: 5s
        #启用健康检查
        enabled: true
        # 配置可以自动剔除，默认不会自动剔除
        deregister: false
        # 服务关闭 30 秒后自动剔除
        health-check-critical-timeout: 30s
```

 3、开启注解

```
@SpringBootApplication
@EnableDiscoveryClient //开启客户端注解
public class SpringCloudConsulApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulApplication.class, args);
    }

}
```



### 参考资料

[pring-cloud-consul 官方文档](https://docs.spring.io/spring-cloud-consul/docs/3.0.2/reference/html/#distributed-configuration-usage)
[spting-cloud-consul 配置文档](https://docs.spring.io/spring-cloud-consul/docs/3.0.2/reference/html/appendix.html)
[consul 官方文档](https://www.consul.io/docs)
[consul 官方下载](https://learn.hashicorp.com/tutorials/consul/get-started-explore-the-ui?in=consul/getting-started)


[docker 安装 consul](https://www.cnblogs.com/summerday152/p/14013439.html)



### PS

查看 springcloud 和 springboot 对应的版本

```
https://start.spring.io/actuator/info
```

