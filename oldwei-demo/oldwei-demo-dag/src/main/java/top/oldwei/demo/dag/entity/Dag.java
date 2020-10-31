package top.oldwei.demo.dag.entity;

import com.clearspring.analytics.util.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Dag implements Serializable {

    private List<Edge> edges = Lists.newArrayList();

    private List<Node> nodes = Lists.newArrayList();

}
