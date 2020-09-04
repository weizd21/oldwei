package top.oldwei.demo.spark.day01;
import com.google.common.collect.Lists;
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
//@Slf4j
public class JavaApp {

    private static Logger logger = Logger.getRootLogger();

    public static void main(String[] args) {

        String csvPath = "/home/weizd/dataset/dataset/sparkApp.csv";

        JavaSparkContext sc = new JavaSparkContext("local[2]","first sc");

        JavaRDD<String[]> data = sc.textFile(csvPath).map(new Function<String, String[]>() {
            @Override
            public String[] call(String s) throws Exception {
                return s.split(",");
            }
        });

        long numPurchases = data.count();

        System.out.println(numPurchases);

    }
}
