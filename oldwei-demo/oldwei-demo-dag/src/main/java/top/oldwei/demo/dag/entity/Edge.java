package top.oldwei.demo.dag.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 *  边
 */
@Data
public class Edge implements Serializable {

    private Long id;

    private long sourceNodeId;

    private long targetNodeId;

    private long sourceOutputIndex;

    private long targetInputIndex;

}
