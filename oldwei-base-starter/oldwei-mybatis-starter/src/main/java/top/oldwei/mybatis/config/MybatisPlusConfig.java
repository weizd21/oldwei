package top.oldwei.mybatis.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import top.oldwei.mybatis.handler.MyMetaObjectHandler;

/**
 * @Author:weizd
 * @Date:20-3-5
 *
 * mybatis plus 插件
 *
 *
 */
@Import(
        MyMetaObjectHandler.class
)
@Configuration
@Slf4j
public class MybatisPlusConfig {


    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        log.info("注入乐观锁插件");
        return new OptimisticLockerInterceptor();
    }



//    /**
//     * SQL执行效率插件
//     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("注入分页插件");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }



}
