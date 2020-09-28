package top.oldwei.demo.dag;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.dag.domain.Dag;
import top.oldwei.demo.dag.domain.Vertice;

import javax.sql.DataSource;
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

        String url = "jdbc:mysql://localhost:3306/dataexa";
        String user = "root";
        String password = "123456";
//        String driver = "";
        DataSource dataSource = new SimpleDataSource(url,user,password);

        List<Entity> list = Db.use(dataSource).findAll("d_dag_flow");
        log.info("size: {}",list.size());
        List<Dag> dags = new ArrayList<>();
        Dag dag = null;
        String dagStr = "";
        for(Entity entity:list){
            log.info(entity.getStr("dag_json"));
            dagStr = entity.getStr("dag_json");
            if(!StrUtil.isEmpty(dagStr)){
                dag = JSONObject.parseObject(dagStr,Dag.class);
                dags.add(dag);
            }
        }

        for (Dag d: dags){
            log.info("start vertice: {}", d.getVerticeByType("start"));
        }


    }
}
