package top.oldwei.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author:weizd
 * @Date:20-2-26
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@Controller
@EnableConfigurationProperties
public class ProductApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ProductApplication.class,args);

        Environment environment = configurableApplicationContext.getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}{}{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path", ""),
                environment.getProperty("spring.mvc.servlet.path", "")
        );

    }





}
