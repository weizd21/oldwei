package top.oldwei.demo.dag.driver;

import org.apache.spark.sql.SparkSession;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/23 下午2:25
 */
public class SparkContextBuilder {


    public static SparkSession getSession(){
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("dag-driver")
                .master("local[*]")
                .config("spark.driver.host", "localhost")
                // 工作目录 默认值为 /tmp
                .config("spark.local.dir", "/tmp")
                .getOrCreate();
        return sparkSession;
    }


}
