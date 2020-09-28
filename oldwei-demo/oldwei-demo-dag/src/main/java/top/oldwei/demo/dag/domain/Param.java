package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 上午11:17
 */
@Data
public class Param implements Serializable {
    private int output;

    private int input;
}
