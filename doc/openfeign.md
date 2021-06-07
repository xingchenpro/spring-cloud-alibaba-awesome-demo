openfeign

本地负载均衡 Ribbon 和 openfeign 客户端

负载均衡算法

轮训

权重

随机

哈希一致性，固定IP

本地负载均衡和服务器负载均衡区别？

本地：负载均衡算法在本地实现，Dubbo，feign/rpc远程调用框架

框架：客户端 Ribbon 负载均衡器，都是在本地实现的

服务器：负载均衡该算法在服务器实现，nginx，负载均衡算法的过程都是在 nginx 实现的，nginx 压力比较大



LoadBalanceClient 可以根据接口名称拿到接口的调用地址



OpenFeig 是一个 web 声明式 http 客户端调用工具，提供接口和注解就能调用

springcloud 第一代采用 feign 第二代采用 opefeign

openfeign 是 springcloud 自己研发，feign 是属于 netfix

openfeign 底层封装 httpclient 技术



openfeign 底层原理？

1、调用接口的时候，使用代理模式，aop，通过反射获取当前接口类上的服务名称

2、获得当前调用方法上的名称，获得调用接口地址，然后进行拼接地址进行调用



openfeign 默认附带 ribbon 实现负载均衡



+ 1、客户端负载均衡和服务器负载均衡区别？
+ 2、常见负载均衡算法？
+ 3、feign 客户端原理？
+ 4、Ribbon 和 feign 区别？
+ 5、Ribbon 能否设置缓存？
+ 6、feign 和 openfeign 的区别？
+ 7、feign 底层原理？
+ 8、feign 客户端继承模式了解吗？
+ 9、手写负载均衡算法？

