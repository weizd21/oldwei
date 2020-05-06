package top.oldwei.netty.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author:weizd
 * @Date:20-4-18
 */
@Slf4j
@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(NettyClientApplication.class,args);

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
