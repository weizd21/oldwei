# oldwei
学习对应的代码工程项目


### TODO LIST

#### 模块
```
|说明|状态|思路|
|----|----|---|
|common模块|未开始||
|dependencies模块|未开始||
|API模块|未开始||
|redis模块|未开始||
|cache模块|未开始||
|订单模块|未开始||
|商品模块|未开始||
|用户模块|未开始||

```


#### 场景

- 订单模块调用商品模块实现用户下单操作
    - 分布式事务
    
- spring-cloud-config配置中心
    - 整合spring-cloud-bus 实现配置动态刷新
    
    





### 技术栈

#### 基础框架
- spring cloud
- spring全家桶
- jpa
- mybatis plus
- 



#### 实用工具框架
- hutool    整合性开源工具
- caffeine  基于Guava优秀的缓存框架
- easy-captcha  基于Java的全面的图像验证码 框架  
- dozer-spring-boot-starte  对象转换工具
- ip2region 精度较高的IP转地址的工具
- UserAgentUtils 根据请求request获取客户端浏览器 ip 等信息
- antisamy 防止XSS工具的
- p6spy 打印springboot执行的sql语句

- javassist 动态修改字节码文件中的方法

- JGroups 分布式通信框架


- https://www.pixijs.com/ 前端引擎

- https://cmder.net/ window下 黑窗口工具

### 服务端口

1. dev
-  oldwei-register-eureka-server 8001
-  oldwei-config-server 8002
-  oldwei-product  8003
-  oldwei-user 8004
-  oldwei-order 8005
-  oldwei-gateway 8006
-  oldwei-gpu-monitor 8007




### 参考文档

- github 项目参考详情
```
  ├── dis-seckill
  ├── distributed-redis-tool
  ├── eladmin
  ├── FEBS-Cloud
  ├── mall-swarm
  ├── netty
  ├── redisson-spring-boot-starter
  ├── redis-spring-boot-starter    基于注解实现/实现 单机、哨兵、集群 自由配置切换
  ├── springboot-cloud
  ├── spring-boot-demo
  ├── spring-boot-klock-starter  限流注解/接口层
  ├── springcloud-learning
  ├── viboot
  └── zuihou-admin-cloud

```



### todo

- 线程编排 https://gitee.com/jd-platform-opensource/asyncTool/blob/master/README.md

- web监控摄像头 https://gitee.com/rancedxk/monitor-rtsp-hls

- sparkjava 微型的web服务开源 https://github.com/perwendel/spark

- hadoop集群搭建 https://foochane.cn/article/2019051901


### 优秀的技术文章

```
http://www.tianshouzhi.com/

https://qinxuewu.github.io/docs/#/

https://redspider.gitbook.io/concurrent/

https://bugstack.cn/

https://z.itpub.net/stackdetail/10131/stackjoin/0

https://cyc2018.github.io/CS-Notes/#/

https://mrbird.cc/

http://cmsblogs.com/

https://javaguide.cn/page/2/

http://www.itmuch.com/





```
