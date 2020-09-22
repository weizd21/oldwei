package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/22 上午10:55
 */
@Data
public class BaseProp implements Serializable {
    private String shape;

    private int width;

    private int height;

    private String color;

    private int border;

    private String borderColor;

    private int x;

    private int y;

    private boolean readonly;

}
