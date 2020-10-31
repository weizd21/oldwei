package top.oldwei.demo.dag;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.dag.domain.Dag;
import top.oldwei.demo.dag.domain.Edge;
import top.oldwei.demo.dag.domain.Parameter;
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

        String url = "jdbc:mysql://localhost:3306/insight-58";
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
            Long id = entity.getLong("id");
            dagStr = entity.getStr("dag_json");
            if(!StrUtil.isEmpty(dagStr) && dagStr.contains("110.48242855072021")){
                log.info("-----------------> {}",id);
                dag = JSONObject.parseObject(dagStr,Dag.class);
                dags.add(dag);
            }
        }
        // 正确的dag流程ID 10217

        for(Edge edge:dag.getEdges()){
            log.info("start :[{}] ,end :[{}]",edge.getStart(),edge.getEnd());
        }


        for(Vertice vertice:dag.getVertices()){
            List<Parameter> parameters = vertice.getParameters();
            if(parameters != null && parameters.size() > 0){
                for (Parameter parameter:parameters){
                    log.info("[{}] ---> [{}]",parameter.getValue(),Action.getParameterValue(parameter));
                }
            }

        }















//        for(Entity entity:list){
//            log.info(entity.getStr("dag_json"));
//            dagStr = entity.getStr("dag_json");
//            if(!StrUtil.isEmpty(dagStr)){
//                dag = JSONObject.parseObject(dagStr,Dag.class);
//                dags.add(dag);
//            }
//        }
//
//        for (Dag d: dags){
//            log.info("start vertice: {}", d.getVerticeByType("start"));
//        }
//

    }
}
