package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 下午2:44
 */
@Data
public class Component implements Serializable {
    private long id;

    private String name;

    private String type;

    private String shape;

    private String description;

    private String icon;

    private String className;

    private String methodName;

    private Parameter[] parameters;

    private String[] inputTypes;

    private String[] outputTypes;

    private int orderNo;

    private boolean isAdvanced;

    private String category;

    private Boolean multiOutput;

    private List<Component> children;

    private Component component;




}
