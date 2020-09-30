package top.oldwei.demo.spark.day01;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-9-1
 */
@Slf4j
public class JavaApp {

    public static void main(String[] args) {
        String csvPath = "/soft/dataset/ml-100k/u.user";
        JavaSparkContext sc = new JavaSparkContext("local[*]","first sc");
        JavaRDD<String[]> data = sc.textFile(csvPath).map(new Function<String, String[]>() {
            @Override
            public String[] call(String s) throws Exception {
                return s.split("|");
            }
        });
        long numPurchases = data.count();

        log.info("line num: {}",numPurchases);
    }
}
