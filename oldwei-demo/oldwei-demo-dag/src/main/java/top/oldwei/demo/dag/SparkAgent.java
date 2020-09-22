package top.oldwei.demo.dag;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.dag.domain.Dag;
import top.oldwei.demo.dag.domain.Vertice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weizd
 */
@Slf4j
public class SparkAgent {

    public static void main(String[] args) throws SQLException {
        log.info("spark agent");

        List<Entity> list = Db.use().findAll("d_dag_flow");

        List<Dag> dags = new ArrayList<>();
        Dag dag = null;
        for(Entity entity:list){
            log.info(entity.getStr("dag_json"));

            dag = JSONObject.parseObject(entity.get("dag_json").toString(),Dag.class);
            dags.add(dag);
        }

        for (Dag d: dags){
            log.info("start vertice: {}", d.getVerticeByType("start"));
        }


    }
}
