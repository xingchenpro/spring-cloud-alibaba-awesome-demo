nocas 配置中心







主流配置中心

SpringCloudConfig

Apollo



传统项目管理配置文件，非常复杂，如果项目已经上线，如果需要改配置文件，需要重新打包发布

1、重启

2、类加载器，热部署，需要定时器定时查询配置有没有改变，不安全，别人修改账号密码，

背景：

传统项目不需要重启，自动刷新配置文件

原理

配置放在 git/数据库

分布式配置中心管理 Potal

配置中心管理服务

配置中心统一管理配置

建立长链接，配置改变，通知本地项目刷新最新的配置文件

netty 服务端与 netty 客户端



Nacos 是轻量级配置中心，部署运维学习成本低

Apollo 非常麻烦





nacos 配置中心一定要采用 booststrap 形式优先加载，否则可能报错

bootstrap.yml 用于应用程序上下文引导阶段，最优先加载

application.yml 由Spring ApplicationContest 加载



默认读取 properties 格式



@RefreshSpope 实时刷新

刷新监听器 RefreshEventListener 

配置发生改变，服务服务端会通知客户端，哪个配置发生改变



 

多版本控制



集群？配置文件共享，data 目录,放到数据库里面





为什么要使用配置中心？

配置中心设计原理?

Nacos 和 Apollo 区别？

配置文件如何实现实时刷新？

配置文件如何保证高可用？

配置文件如何实现多版本控制？







