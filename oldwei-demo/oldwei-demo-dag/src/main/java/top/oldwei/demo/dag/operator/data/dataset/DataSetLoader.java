package top.oldwei.demo.dag.operator.data.dataset;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import top.oldwei.demo.dag.constant.FileTypeConstant;
import top.oldwei.demo.dag.driver.SparkContextBuilder;
import top.oldwei.demo.dag.operator.base.BaseOperator;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/23 下午1:33
 *
 * 加载数据集形成DataSet格式
 *
 */
public class DataSetLoader implements BaseOperator {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");


    public Dataset<Row> load(String path,String type,Boolean header,String separate){
        SparkSession session = SparkContextBuilder.getSession();
        DataFrameReader reader = session.read();
        if (header != null) {
            reader.option("header", header);
        }
        if (separate != null) {
            reader.option("sep", separate);
        }
        return load(reader,type,path);
    }


    public Dataset<Row> load(DataFrameReader reader,String type,String path){
        if(FileTypeConstant.CSV.equals(type)){
            return csv(reader,path);
        } else if (FileTypeConstant.TEXT.equals(type)) {
            return text(reader,path);
        } else if (FileTypeConstant.PARQUET.equals(type)) {
            return parquet(reader,path);
        } else if (FileTypeConstant.JSON.equals(type)) {
            return json(reader,path);
        } else if(FileTypeConstant.ORC.equals(type)){
            return orc(reader,path);
        }else {
            return null;
        }
    }



    public static Dataset<Row> text(String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return text(paths);
    }
    public static Dataset<Row> text(DataFrameReader reader,String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return text(reader,paths);
    }
    public static Dataset<Row> text(String[] paths) {
        SparkSession session = SparkContextBuilder.getSession();
        return session.read().text(paths);
    }
    public static Dataset<Row> text(DataFrameReader reader,String[] paths) {
        return reader.text(paths);
    }


    public static Dataset<Row> parquet(String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return parquet(paths);
    }
    public static Dataset<Row> parquet(DataFrameReader reader,String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return parquet(reader,paths);
    }
    public static Dataset<Row> parquet(String[] paths) {
        SparkSession session = SparkContextBuilder.getSession();
        return session.read().parquet(paths);
    }
    public static Dataset<Row> parquet(DataFrameReader reader,String[] paths) {
        return reader.parquet(paths);
    }



    public static Dataset<Row> csv(String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return csv(paths);
    }
    public static Dataset<Row> csv(DataFrameReader reader,String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return csv(reader,paths);
    }
    public static Dataset<Row> csv(String[] paths) {
        SparkSession session = SparkContextBuilder.getSession();
        return session.read().csv(paths);
    }
    public static Dataset<Row> csv(DataFrameReader reader,String[] paths) {
        return reader.csv(paths);
    }




    public static Dataset<Row> json(DataFrameReader reader,String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return json(reader,paths);
    }
    public static Dataset<Row> json(String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return json(paths);
    }
    public static Dataset<Row> json(String[] paths) {
        SparkSession session = SparkContextBuilder.getSession();
        return json(session.read(),paths);
    }
    public static Dataset<Row> json(DataFrameReader reader,String[] paths) {
        return reader.json(paths);
    }



    public static Dataset<Row> orc(String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return orc(paths);
    }
    public static Dataset<Row> orc(DataFrameReader reader,String path) {
        String[] paths = path.split(LINE_SEPARATOR);
        return orc(reader,paths);
    }
    public static Dataset<Row> orc(String[] paths) {
        SparkSession session = SparkContextBuilder.getSession();
        return session.read().orc(paths);
    }
    public static Dataset<Row> orc(DataFrameReader reader,String[] paths) {
        return reader.orc(paths);
    }



}
