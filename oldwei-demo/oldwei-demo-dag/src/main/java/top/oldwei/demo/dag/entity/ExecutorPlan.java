package top.oldwei.demo.dag.entity;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.*;

@Slf4j
public class ExecutorPlan {

    private static Queue<Node> zeroInputQueue = new LinkedList<>();

    private static Map<Long,Node> id2Node = Maps.newHashMap();

    private static Map<Long,Integer> id2InputNum = Maps.newHashMap();

    private static Map<Long, Set<Long>> id2PreIds = Maps.newHashMap();


    public static void init(@NotNull Dag dag){

        for(Node node:dag.getNodes()){
            id2Node.put(node.getId(),node);
            id2InputNum.put(node.getId(),0);
            id2PreIds.put(node.getId(), new HashSet<>());
//            if(node.getName().contains("开始")){
//                zeroInputQueue.add(node);
//            }
        }
        for(Edge edge:dag.getEdges()){
            id2InputNum.put(edge.getTargetNodeId(),id2InputNum.get(edge.getTargetNodeId())+1);
            id2PreIds.get(edge.getTargetNodeId()).add(edge.getSourceNodeId());
        }

        for(Map.Entry<Long,Set<Long>> entry:id2PreIds.entrySet()){
            if(entry.getValue().size() <= 0){
                log.info("----> 加入队列：【{}】",id2Node.get(entry.getKey()).getName());
                zeroInputQueue.add(id2Node.get(entry.getKey()));
            }
        }
    }


    public static void execute(){
        Node node = null;
        while (!zeroInputQueue.isEmpty()){
            node = zeroInputQueue.poll();
            log.info("----> node :[{}]",node.getName());
            for(Node node1:id2Node.values()){
                if(id2PreIds.get(node1.getId()).contains(node.getId())){
                    //
                    id2PreIds.get(node1.getId()).remove(node.getId());
                    if(id2PreIds.get(node1.getId()).size() <= 0){
                        zeroInputQueue.add(node1);
                    }
                }
            }
        }
    }



}
