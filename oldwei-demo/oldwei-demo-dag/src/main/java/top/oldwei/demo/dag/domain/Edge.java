package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 上午10:51
 */
@Data
public class Edge extends BaseProp {

    private Long start;

    private Long end;

    private String type;

    private List<Param> parameters = new ArrayList<>();

    private Long source;

    private Long target;




}
