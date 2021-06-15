# springcloudalibaba 微服务



## 目录

##### [一、微服务架构简介](#一、微服务架构简介)
##### [二、服务注册与发现 Nacos](二、服务注册与发现 Nacos)
##### [三、负载均衡 loadbalancer](三、负载均衡 loadbalancer)
##### [四、OpenFeign 客户端](四、OpenFeign 客户端)
##### [五、分布式配置中心 Nacos](五、分布式配置中心 Nacos)
##### [六、网关 GateWay](六、网关 GateWay)
##### [七、服务保护 Sentinel](七、服务保护 Sentinel)



## 一、微服务架构简介

### 架构演变过程


#### 1、传统架构

```
传统的架构，也就是为单点应用，也就是我们在早期的 JavaWab、SSH 或者 SSM 架构模式，会采用分层架构模式：数据库访问层、业务逻辑层、控制层，从前端到后台所有的代码都是一个开发者去完成。

该架构模式没有对我们业务逻辑代码实现拆分，所有的代码都写入到同一个工程中里面，适合于小公司开发团队或者个人开发，比如一些学校、政府项目。

这种架构模式最大的缺点，如果该系统一个模块出现不可用、需要维护，会导致整个系统无法使用。
```

#### 2、分布式架构

```
分布式架构模式是基于传统的架构模式演变过来，将传统的单点项目根据业务模块实现拆分、会拆分为会员系统、订单系统、支付系统、秒杀系统等, 从而降低我们项目的耦合度,一个项目可能会链接多个数据库，有多个数据源，从而查询不同服务数据信息，这种架构模式开始慢慢的适合于互联网公司开发团队。
```

#### 3、SOA 架构

```
SOA 架构模式也称作为：面向服务架构模式、俗称面向与接口开发，将共同存在的业务逻辑抽取成一个共同的服务，提供给其他的服务接口实现调用、服务与服务之间通讯采用 rpc 远程调用技术、WebService 技术。

这种架构没有注册中心，通常会在数据库建立一张表，保存服务名和服务调用地址，在需要进行远程调用的时候，从数据库中查表。
```

#### 4、微服务架构

```
微服务架构模式是从SOA架构模式演变过来， 比 SOA 架构模式粒度更加精细，让专业的人去做专业的事情，目的是提高效率，每个服务与服务之间互不影响，微服务架构中。

引入注册中心，采用服务注册于发现机制，使服务化管理，服务治理更加完善。

微服务强调独每个服务都是单独数据库，独立部署，保证每个服务于服务之间互不影响。微服务架构模式体现轻巧、轻量级、适合于互联网公司开发模式。

服务与服务通讯协议采用 Http 协议，使用 RESTful 风格 API 形式来进行通讯，数据交换格式轻量级 json 格式通讯，整个传输过程中，采用二进制，所以 http 协议可以跨语言平台，并且可以和其他不同的语言进行相互的通讯，所以很多开放平台都采用 http 协议接口。
```



### 微服务常用组件和解决方案

注册中心：eureka、zookeeper、consule、nacos、etcd

配置中心：config、apollo、nacos、zookeeper、etcd

网关：zuul、gateway、kong

远程调用与负载均衡：ribbon、loadBalance、opfenFeign

服务保护：hystrix、sentinel

服务追踪与调用链： zipkin 等

分布式事务解决方案：rabbitmq(最终一致性)、rocketmq(事务消息)、lcn(AT，已淘汰)、seata(强一致性)

缓存一致性解决方案：canal



### 为什么要使用 SpringCloud，SpringCloud 和 SpringCloud Alibaba 区别？

SpringCloud 一套全家桶的微服务解决框架，目的就是解决我们在微服务架构中遇到的任何问题。

SpringCloud Alibaba 是阿里对 SpringCloud 组件的扩展，目的是为了推广阿里云的产品。



+ SpringCloud 微服务解决方案：

```
SpringCloud Config 分布式配置中心

SpringCloud Netflix 核心组件

Eureka:服务治理 

Hystrix:服务保护框架

Ribbon:客户端负载均衡器

Feign：基于ribbon和hystrix的声明式服务调用组件

Zuul: 网关组件,提供智能路由、访问过滤等功能。
```

+ SpringCloud Alibaba 微服务解决方案：

 ```

Spring Cloud Gateway 网关

Spring Cloud Loadbalancer 客户端负载均衡器

Spring Cloud Alibaba Nacos 服务注册

Spring Cloud Alibaba Nacos 分布式配置中心

Spring Cloud Alibaba Sentinel 服务保护 

SpringCloud Alibaba Seata分布式事务解决框架

Alibaba Cloud OSS 阿里云存储

Alibaba Cloud SchedulerX 分布式任务调度平台

Alibaba Cloud SMS 分布式短信系统
 ```



### Nacos 、 Zookeeper、Eureka  区别

#### CPA 定律

这个定理的内容是指的是在一个分布式系统中、Consistency（一致性）、Partition tolerance（分区容错性） 、Availability（可用性）、三者不可得兼，要么是 AP，要么是 CP。

一致性(C)：在分布式系统中，如果服务器集群，每个节点在同时刻访问必须要保持数据的一致性（强一致性）

可用性(A)：集群节点中，部分节点出现故障后任然可以使用 （高可用）

分区容错性(P)：在分布式系统中网络会存在脑裂的问题，部分Server与整个集群失去节点联系，无法组成一个群体。

只有在 CP 和 AP 选择一个平衡点

#### Eureka 、 Zookeeper、Nacos  实现上区别

相同点：

三者都可以实现分布式注册中心框架

不同点：

Zookeeper采用 CP 保证数据的一致性的问题，原理采用(ZAB原子广播协议，Zookeeper Atomic Broadcast)，当我们 ZK 领导者因为某种情况下部分节点出现了故障，会自动重新实现选举新的领导角色，整个选举的过程中为了保证数据一致性的问题，客户端暂时无法使用我们的 Zookeeper，整个微服务无法实现通讯。

Eureka 采用 AP 设计思想实现分布式注册中心，完全去中心化、每个节点都是相等，采用你中有我、我中有你相互注册设计思想， 只要最后有一台Eureka节点存在整个微服务就可以实现通讯。

Nacos从1.0 版本选择 AP 和 CP 混合形式实现注册中心，默认情况下采用 AP，CP 则采用 Raft 协议实现保持数据的一致性。
如果选择为 AP 模式，注册服务的实例仅支持临时模式，在网络分区的的情况允许注册服务实例，选择 CP 模式可以支持注册服务的实例为持久模式，在网络分区的产生了抖动情况下不允许注册服务实例。

Nacos 修改为 CP

```
http://123.57.173.162:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP
```



## 二、服务注册与发现 Nacos



### 服务治理的概念

在 RPC 远程调用过程中，服务与服务之间依赖关系非常大，服务 Url 地址管理非常复杂，所以这时候需要对我们服务的url实现治理，通过服务治理可以实现服务注册与发现、负载均衡、容错等。

### 注册中心的概念

每次调用该服务如果地址直接写死的话，一旦接口发生变化的情况下，这时候需要重新发布版本才可以该接口调用地址，所以需要一个注册中心统一管理我们的服务注册与发现。

注册中心：我们的服务注册到我们注册中心，key 为服务名称、value 为该服务调用地址，该类型为集合类型。Eureka、consul、zookeeper、nacos 等。

服务注册：我们生产者项目启动的时候，会将当前服务自己的信息地址注册到注册中心。

服务发现: 消费者从我们的注册中心上获取生产者调用的地址（集合），在使用负载均衡的策略获取集群中某个地址实现本地 rpc 远程调用。

### Nacos 环境搭建

1、下载安装包

```
https://github.com/alibaba/nacos/releases
```

2、bin 目录执行命令

```
# window
startup.cmd -m standalone

# linux
sh startup.sh -m standalone
```

3、访问

```
http://123.57.173.162:8848/nacos
```

```
用户名/密码: nacos/nacos
```



### 搭建服务提供者(被调用服务)

1、添加 maven 依赖

```
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```

2、启动类开启服务发现注解

```
@EnableDiscoveryClient
```

3、application.yml 配置文件

```
spring:
  cloud:
    nacos:
      discovery:
      # nacos服务器地址
        server-addr: 123.57.173.162:8848
  application:
    name: provider-service
server:
  port: 8084
```

4、IDEA 设置修改端口启动多个实例，访问。

```
http://localhost:8082/index
```



## 三、负载均衡 loadbalancer

### 搭建服务消费者

添加 maven 依赖

```
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>
```

开启注解

```
@EnableDiscoveryClient
```

LoadBalancerClient 客户端实现`本地负载均衡`，轮训调用服务。

```
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
```

```
    @Resource
    private LoadBalancerClient loadBalancerClient;
```

测试

```
http://localhost:8081/test1
```



### 本地负载均衡与 Nginx 负载均衡区别？

本地负载均衡器基本的概念：消费者服务从我们的注册中心获取到集群地址列表，缓存到本地，让后本地采用负载均衡策略（轮训、随机、权重等），调用远程服务。

Nginx负载均衡是客户端所有的请求统一都交给我们的Nginx处理，让后在由Nginx实现负载均衡转发，属于服务器端负载均衡器。



## 四、OpenFeign 客户端

OpenFeign 是一个 Web 声明式的 Http 客户端调用工具，提供接口和注解形式调用。

###  OpenFeign 客户端搭建

1、添加 maven 依赖

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

2、提供被调用服务接口

```
@FeignClient(value = "provider-service")
public interface OpenFeignClient {

    @GetMapping("/index")
    String openFeignTest();

}
```

3、使用 feign 客户端进行调用

```
    @Resource
    private OpenFeignClient openFeignClient;
```

```
    public String consumer2() {
        return openFeignClient.openFeignTest();
    }
```

4、访问

```
http://localhost:8081/test2
```

5、nacos 支持服务实例上下线

## 五、分布式配置中心 Nacos

### 分布式配置中心作用

分布式配置中心可以实现不需要重启我们的服务器，动态的修改我们的配置文件内容，常见的配置中心有携程的 Apollo、SpringCloud Config、Zookeeper、Nacos 轻量级的配置中心等。

### 搭建 Nacos 配置中心

1、添加 maven 依赖

```
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
```

2、yml 配置文件

```
spring:
  application:
    name: consumer-service
  cloud:
    nacos:
      discovery:
        # nacos服务器地址
        server-addr: 123.57.173.162:8848
        service: ${spring.application.name}
      config:
        ###配置中心连接地址
        server-addr: 123.57.173.162:8848
#        ###分组
        group: DEFAULT_GROUP
        ###类型
        file-extension: yaml
#  profiles:
#    active: dev

server:
  port: ${PORT:8081}

#stardust:
#  username: hly
```

3、测试

修改配置中心配置再次刷新，配置文件已经修改。

```
http://localhost:8081/test3
```



### 搭建过程需要注意的点

1、配置文件的名称为 bootstrap.yml , bootstrap.yml 的优先级高于 application.yml, 使用 application.yml 会报错，无法获得配置。

2、SpringCloud 高版本默认不开启 bootstrap.yml ，需要手动开启

需要添加依赖

```
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bootstrap</artifactId>
            </dependency>
```

然后添加虚拟机参数开启 bootstrap.yml 配置。

```
-Dspring.cloud.bootstrap.enabled=true
```

3、需要在配置文件中指定配置文件的类型，默认为 properties 类型

```
file-extension: yaml
```

4、在配置作用范围内添加注解 @RefreshScope

```
@RefreshScope//实现配置，实例热加载
```



### 多版本控制

1、创建不同环境的配置

```
consumer-service-dev.yaml
consumer-service-prd.yaml
```

2、bootstrap.yml 指定配置中心配置文件

```
spring:
  profiles:
    active: prd
```





## 六、网关 GateWay



### 微服务网关简介

微服务网关是整个微服务 API 请求的入口，可以实现日志拦截、权限控制、解决跨域问题、
限流、熔断、负载均衡、黑名单与白名单拦截、授权等。

在 SpringCloud 中， Zuul 网 Gateway属于 SpringCloud 自研发的第二代微服务网关

关属于netfix公司开源的产品属于第一代微服务网关

### GateWay 环境搭建

1、添加 maven 依赖

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
```

2、application.yml 配置

```
server:
  port: 8888

####服务网关名称
spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      discovery:
        locator:
          # 开启以服务id去注册中心上获取转发地址
          enabled: true
      routes:
        # 路由 id
      - id: provider-route
        # 基于 lb 负载均衡形式转发
        uri: lb://provider-service
        predicates:
        - Path=/provider/**
        filters:
        - StripPrefix=1
      - id: consumer-route
        uri: lb://consumer-service
        predicates:
        - Path=/consumer/**
        filters:
        - StripPrefix=1
    nacos:
      discovery:
        server-addr: 123.57.173.162:8848
```

3、测试

```
http://localhost:8888/provider/index
```

```
http://localhost:8888/api/consumer/test1
```

## 七、服务保护 Sentinel



### 服务限流

服务限流目的是为了更好的保护我们的服务，在高并发的情况下，如果客户端请求的数量达到一定极限（后台可以配置阈值），请求的数量超出了设置的阈值，开启自我的保护，返回一个友好的提示。


### 服务的雪崩效应

默认的情况下，Tomcat 或者是 Jetty 服务器只有一个线程池去处理客户端的请求，这样的话就是在高并发的情况下，如果客户端所有的请求都堆积到同一个服务接口上， 那么就会产生 tomcat 服务器所有的线程都在处理该接口，可能会导致其他的接口无法访问。

假设我们的 tomcat 线程最大的线程数量是为 200，这时候客户端如果同时发送 300个请求会导致有 100 个请求暂时无法访问，就会转圈。

### 熔断机制

熔断机制产生的背景是“雪崩效应”，在分布式系统中，一个服务提供者的不可用会导致服务消费者的不可用，一个服务的异常，最终会影响其他服务，造成线程阻塞，资源耗尽，如果有大量的请求，会导致服务瘫痪，引蝴蝶效应造成系统宕机。

如果一个服务的错误过多，短时间内得不到修复，就可以开启熔断机制，防止多次没有意义的调用。服务调用方会定时重试，如果可用，就继续使用。

熔断有以下几种状态：

闭合状态
添加一个计数器，如果失败的次数在指定时间内超过一定阈值，则开启熔断，此时处于**断开状态**，此时开启一个计时器，如果过了指定时间，则切换到**半开状态**，服务可被尝试调用。

断开状态
服务如果被调用会立即返回错误信息，减少资源损耗。

半开状态
允许一定数量的请求调用服务，如果请求调用成功，则关闭熔断，认为服务的错误已经修正。如果还是发生错误，则切换到断开状态。

### 服务熔断/降级

在高并发的情况下， 防止用户一直等待，采用熔断/降级方法，使用服务降级的方式返回一个友好的提示给客户端，不会执行业务逻辑请求，直接走本地的 falback 的方法。

### Sentinel 与 Hystrix 区别

1.丰富的应用场景：Sentinel 承接了阿里巴巴近10年的双十一大促流的核心场景，例如秒杀（即突然流量控制在系统容量可以承受的范围），消息削峰填谷，传递流量控制，实时熔断下游不可用应用等。

2.完备的实时监控：Sentinel 同时提供实时的监控功能。您可以在控制台中看到接收应用的单台机器秒级数据，甚至 500台以下规模的整合的汇总运行情况。

广泛的开源生态：Sentinel 提供开箱即用的与其他开源框架/库的集成模块，例如与Spring Cloud，Dubbo，gRPC 的整合。您只需要另外的依赖并进行简单的配置即可快速地接入Sentinel。

3.完善的 SPI 扩展点：Sentinel 提供简单易用，完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理，适应动态数据源等。

<div style ="float:left">

![sentinel&hystrix](D:\stardust\spring-cloud-alibaba-awesome-demo\doc\sentinel&hystrix.png)  

</div>

### Sentinel 控制台安装搭建

1、下载控制台

```
https://github.com/alibaba/Sentinel/releases
```

2、启动控制台

```
java -Dserver.port=8718 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -Dcsp.sentinel.api.port=8719 -jar sentinel-dashboard-1.8.1.jar
```

启动参数

```
-Dserver.port=8718 控制台端口 sentinel 控制台是一个 spring boot 程序,可以通过这个端口访问控制台。

-Dcsp.sentinel.dashboard.server=localhost:8718 向控制台发送心跳包的控制台地址，指定控制台后客户端会自动向该地址发送心跳包。

-Dcsp.sentinel.api.port=8719 API 端口，客户端提供给 Dashboard 访问或者查看Sentinel 的运行访问的参数,默认8719

-Dproject.name=sentinel-dashboard 指定Sentinel控制台程序的名称
```

3、访问控制台

```
http://localhost:8718/#/dashboard
```

```
用户名/密码：sentinel/sentinel
```

### SpringBoot 整合 Sentinel

1、添加 maven 依赖

```
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```

2、application.yml 配置

```
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8718
      eager: true
  application:
    name: sentinel-service
server:
  port: 8085
```

### 限流配置

参数配置

```
QPS:设置 QPS 为1 表示每s最多能够访问1次接口。

线程数:设置阈值为 1,每次最多只会有一个线程处理该业务逻辑，超出该阈值的情况下，直接拒绝访问
```

示例代码

```
    /**
     * 限流测试
     * blockHandler：限流出现异常执行的方法
     * @return
     */
    @SentinelResource(value = "testLimit",blockHandler = "testLimitHandler")
    @RequestMapping("/testLimit")
    public String testLimit() {
        return "接口正常访问";
    }

    public String testLimitHandler(BlockException e) {
        return "接口已经被限流";
    }
```

测试限流，规定时间只能访问规定次数

```
http://localhost:8085/testLimit
```

### 熔断降级配置

```
1.平均响应时间 (DEGRADE_GRADE_RT)：当 1s 内持续进入 5 个请求，对应时刻的平均响应时间（秒级）均超过阈值（count，以 ms 为单位），那么在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 DegradeException）。注意 Sentinel 默认统计的 RT 上限是 4900 ms，超出此阈值的都会算作 4900 ms，若需要变更此上限可以通过启动配置项 -Dcsp.sentinel.statistic.max.rt=xxx 来配置。

2.异常比例 (DEGRADE_GRADE_EXCEPTION_RATIO)：当资源的每秒请求量 >= 5，并且每秒异常总数占通过量的比值超过阈值（DegradeRule 中的 count）之后，资源进入降级状态，即在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，对这个方法的调用都会自动地返回。异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%。

3.异常数 (DEGRADE_GRADE_EXCEPTION_COUNT)：当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别的，若 timeWindow 小于 60s，则结束熔断状态后仍可能再进入熔断状态。
```

示例代码

```
    /**
     * 熔断降级测试
     * fallback：降级执行的方法
     * @throws Exception
     */
    @SentinelResource(value = "testFallBack",fallback = "testFallBackHandler")
    @RequestMapping("/testFallBack")
    public String testFallBack() throws Exception {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        return "接口正常访问";
    }

    public String testFallBackHandler() {
        return "接口已经被熔断降级";
    }
```

测试熔断降级

```
http://localhost:8085/testFallBack
```

### 热点规则配置（秒杀）

代码示例

```
    @RequestMapping("/seckill")
    @SentinelResource(value ="seckill", fallback = "seckillFallback", blockHandler = "seckillBlockHandler")
    public String seckill(Long userId, Long orderId) {
        return "秒杀成功";
    }
```

```
@RestControllerAdvice
public class InterfaceExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ParamFlowException.class)
    public String businessInterfaceException(ParamFlowException e) {
        return "您当前访问的频率过高，请稍后重试!";
    }
}
```

测试秒杀

```
http://localhost:8085/seckill?userId=123456&orderId=644064779
http://localhost:8085/seckill?userId=123457&orderId=644064779
```

### 参考资料



> springcloudalibaba github：https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md
>
> nacos 官网：https://nacos.io/zh-cn/docs/deployment.html
>
> nacos github：https://github.com/alibaba/nacos
>
> sentinel github：https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
>
> sentinel dashboard github：https://github.com/alibaba/Sentinel/wiki/Dashboard
>
> 

> @RefreshScope 介绍：https://blog.csdn.net/zzzgd_666/article/details/84322947