package top.oldwei.demo.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.time.LocalDateTime;

/**
 * @Author:weizd
 * @Date:20-1-9
 */
public class ReadCSV {


    public static void main(String[] args) {

        String separator = ",";

        String title = "false";

        String address = "/home/weizd/dataset/dataset/abalone_nohead.csv";

        address = "/home/weizd/dataset/test2";

        SparkSession sparkSession = SparkSession.builder()
                .appName("datastax-insight")
                .master("local[*]")
                .config("spark.driver.host", "localhost")
                // 工作目录 默认值为 /tmp
//                .config("spark.local.dir", tmpLocation)
                .getOrCreate();

        Dataset<Row> dataset =  sparkSession.read().option("header", "false").option("delimiter", separator)
                .option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ").csv(address);



        dataset.printSchema();



        System.out.println(dataset.count());


        dataset.show(400);


        dataset.filter("_c0 > 0").orderBy("_c0").show();


        dataset.filter("_c1 == 731.0").orderBy("_c0").show();


        dataset.orderBy("_c0").show();



        String[] columns = dataset.columns();

        for(String column : columns){
            System.out.println(column);
        }



        System.out.println(LocalDateTime.now());

    }

}
