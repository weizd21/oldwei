package top.oldwei.demo.neo4j.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.graph.Graph;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.internal.value.NodeValue;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.util.Pair;
import top.oldwei.demo.neo4j.Neo4jApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-6-8
 */
public class Neo4jTest {


    public static void main(String[] args) {




        //        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j","wzd112358"));
        Driver driver = GraphDatabase.driver("bolt://192.168.1.149:7688", AuthTokens.basic("neo4j","dataexa"));

        String query = "MATCH (:Movie {title:{title}})<-[:ACTED_IN]-(a:Person) RETURN a.name as actor";

        query = "MATCH(n) return n limit 20";



        query = "MATCH(n) return n.name,id(n) as id limit 20";

//        query = "MATCH p=()-[r:`丈夫`]->() RETURN p LIMIT 25";

        try (Session session = driver.session()) {
            Map<String,Object> params = new HashMap<>();
//            params.put("num", 25);
//            Result result = session.run(query);
//            while (result.hasNext()) {
//                System.out.println(result.next().get("n").type().name());
//            }


            Result result = session.run(query,params);

            result.stream().forEach(record -> {
//                System.out.println(JSONObject.toJSON(record.values()));

                Map<String,Object> map = record.asMap();

                for(Map.Entry<String,Object> entry:map.entrySet()){
//                    System.out.print(" key:"+entry.getKey()+"---->");
//                    System.out.println(" value:"+entry.getValue());
                }

                List<Pair<String, Value>> pairList = record.fields();
                pairList.stream().forEach(p->{
//                    System.out.print(" key:"+p.key()+"---->");
//                    System.out.println(" value:"+p.value());

                    Value value = p.value();
                    if(value instanceof NodeValue){
                        System.out.println(value.type().name());
                        System.out.println("Node ...");
                        Node node = value.asNode();

                        System.out.println(node.id());
                        System.out.println(JSONObject.toJSON(node.asMap()));
                        System.out.println(node.labels());

                    }else if(value instanceof PathValue){
                        System.out.println(value.type().name());

                        for (Node node: value.asPath().nodes()){
                            System.out.println(JSONObject.toJSON(node.asMap()));
                        }

                        for (Relationship relationship: value.asPath().relationships()){
                            System.out.println(JSONObject.toJSON(relationship.type()));
                        }

                        System.out.println("Path ... ");
                    }else {
                        System.out.println(value.type().name());
                        System.out.println(" Properties ...");

//                        System.out.println(JSONObject.toJSON(p.key()));
//                        System.out.println(JSONObject.toJSON(p.value()));
//                        JSONObject jsonObject = new JSONObject();
//
//                        System.out.println(value.getClass());
//
//                        if(value instanceof ListValue){
//                            jsonObject.put(p.key(),value.asList());
//                        }else if(value instanceof StringValue){
//                            jsonObject.put(p.key(),value.asString());
//                        }else if(value instanceof IntegerValue){
//                            jsonObject.put(p.key(),value.asInt());
//                        }
//                        System.out.println(jsonObject);


                    }
                });






            });


//            List<Map<String,Object>> list = session.run(query,params).list(r -> r.asMap(Neo4jTest::convert));
//
//
//            for(Map<String,Object> map:list){
//                System.out.println(map);
//            }

        }
    }






    static Object convert(Value value) {
        switch (value.type().name()) {
            case "PATH":
                return value.asList(Neo4jTest::convert);
            case "NODE":
            case "RELATIONSHIP":
                return value.asMap();
        }
        return value.asObject();
    }

}
