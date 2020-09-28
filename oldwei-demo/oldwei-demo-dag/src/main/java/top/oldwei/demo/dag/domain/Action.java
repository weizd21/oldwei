package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 下午3:00
 */
@Data
public class Action extends Component{

    private int level;

    private List<Action> nextActions = new ArrayList<>();

    private List<Action> preActions = new ArrayList<>();

    private Map<Integer, Long> preActionOrder = new HashMap<>();

    private Object[] paramValues;

    private long[] actions;

    private Object result;

    private long[] paramOrders;

    private Dag dag;


}
