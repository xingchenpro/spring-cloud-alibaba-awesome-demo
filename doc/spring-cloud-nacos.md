### 一、简介





### 二、下载

+ 下载 nacos，进行解压

```
https://github.com/alibaba/nacos/releases
```

+ 在 bin 目录下执行命令

```
# window
startup.cmd -m standalone
```

```
# linux
sh startup.sh -m standalone
```

+ 访问

```
http://123.57.173.162:8848/nacos
```

+ 用户名密码

```
nacos/nacos
```



### 三、服务提供者搭建





### 四、服务消费者搭建





### 五、测试

```
127.0.0.1:8081/test
```





配置中心报错

https://blog.csdn.net/qq_39598180/article/details/105745650



```
在获取配置的类上加上@RefreshScope

bootstrap

重启

springboot 版本不兼容

制定配置文件类型
```



```
http://123.57.173.162:8848/nacos/v1/cs/configs?dataId=consumer-service-dev.yaml&group=DEFAULT_GROUP
```





