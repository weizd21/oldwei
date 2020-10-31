package top.oldwei.demo.dag.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class Node extends SuperProp{

    private long id;

    private String name;

    private String type;

    private String className;

    private String methodName;

    private String icon;

    private String nodeStyle;
    /**
     * 节点入点 [0,1,2,3]
     */
    private List<Integer> inPorts = Lists.newArrayList();
    /**
     * 节点出点数组 [0,1,2,3]
     */
    private List<Integer> outPorts = Lists.newArrayList();
    /**
     * 节点使用的参数列表
     */
    private List<Parameter> parameters = Lists.newArrayList();

}
