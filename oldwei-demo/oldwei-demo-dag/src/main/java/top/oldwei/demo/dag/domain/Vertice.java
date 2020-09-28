package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizd
 */
@Data
public class Vertice extends BaseProp{

    private long id;

    private String name;

    private String value;

    private String type;

    private String description;

    private String paramOrders;

    private String actions;

    private List<String> labels;

    private List<Parameter> parameters = new ArrayList<>();



}
