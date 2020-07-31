## springboot 项目打包成war包 直接引用 其他springboot jar项目

### 背景
```
外部提供了可以直接运行的springboot的jar包项目,不能修改源码;但是自己想在jar包的基础上添加新的功能.
所以想到此处: 新建springboot war包项目, 引入给的jar项目. 然后讲 war包里面添加新的功能.
最后讲war部署在 tomcat下面

```

### 步骤

#### 在war项目 pom 配置点

- 打包方式
```
    <packaging>war</packaging>
```
- 禁用内置的tomcat
```
        <!--部署成war包时开启↓↓↓↓-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>
        <!--部署成war包时开启↑↑↑↑-->

```
- 打包war插件
```
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.6</version>
            <configuration>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
```

- 引用其他的springboot jar项目的包
```
说明: 因为我这个是在一个工程里面,所以打包后在本地仓库中有这个jar,就可以直接引入. 如果是外部提供的jar,则使用 绝对路径的方式引入即可.

        <dependency>
            <groupId>top.oldwei</groupId>
            <artifactId>oldwei-demo-jar</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

```

- war整体pom如下
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>oldwei-dependencies</artifactId>
        <groupId>top.oldwei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>oldwei-demo-war</artifactId>

    <packaging>war</packaging>

    <dependencies>

        <dependency>
            <groupId>top.oldwei</groupId>
            <artifactId>oldwei-demo-jar</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--部署成war包时开启↓↓↓↓-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>
        <!--部署成war包时开启↑↑↑↑-->



    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.6</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

#### war项目中代码变动点

- 启动类
    - 继承 SpringBootServletInitializer并 重写configure方法
    - 添加 ComponentScan注解, 主要是添加 引入jar项目的 包路径.这样就可以读取 jar项目的各种 component类了.
    
```

package top.oldwei.demo.war;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:weizd
 * @Date:20-7-31
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"top.oldwei"})
public class WarApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        log.info("WarApplication main ........");
        SpringApplication.run(WarApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WarApplication.class);
    }
}


```
#### war包项目的application.yml改动
```

复制引入 jar项目的配置. 新的war项目,配置以 war项目里的配置为准
    
```






