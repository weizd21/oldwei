## spring cloud config 配置中心

### 说明
1. 配置中心服务端单独使用,不注册到eureka注册中心中去,其他的服务可以通过设置uri找到对应的配置中心

2. 配置中心服务注册到注册中心中去,其他的服务可以借助注册中心,找到对应的配置中心


### 配置
#### server端
- pom.xml
```

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>


<!--<dependency>-->
    <!--<groupId>org.springframework.cloud</groupId>-->
    <!--<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>-->
<!--</dependency>-->

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
    
```
- bootstrap.yml
```
spring:
  application:
    name:  @pom.config.server.name@
  profiles:
    active: @pom.profile.name@
  cloud:
    config:
      server:
        git:
          uri: https://github.com/weizd21/oldwei.git #GitHub上面的git仓库名字
          label: develop # 分支
          search-paths: oldwei-config-file # 搜索的目录
          username:
          password:
```

#### client端
- pom.xml
```


```
- bootstrap.yml
```
spring:
  application:
    name: @pom.order.name@
  profiles:
    active: @pom.profile.name@
  cloud:
    config:
      name: ${spring.application.name},oldwei-eureka,oldwei-zookeeper,oldwei-redis,oldwei-db
      profile: @pom.profile.name@
      label: @pom.config.label@
      uri: @pom.config.uri@
      # 是否使用eureka注册中心机制
#      discovery:
#        service-id: @pom.config.server.name@
#        enabled: true                        #开启Config服务发现支持
      #配置重试机制
      retry:
        initial-interval: 2000
        max-attempts: 2000
        max-interval: 2000
        multiplier: 1.2
      fail-fast: true

```
