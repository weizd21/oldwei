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
